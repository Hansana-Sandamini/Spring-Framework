$(document).ready(function() {
    // Global variables
    let jobs = [];
    const baseUrl = "http://localhost:8080/api/v1/job";
    let currentPage = 0;
    const pageSize = 5;
    let totalPages = 0;
    let currentKeyword = '';

    // Initialize the page
    loadJobs(currentPage);

    // Load jobs from backend with pagination
    function loadJobs(page = 0, keyword = '') {
        currentPage = page;
        currentKeyword = keyword;

        let url = `${baseUrl}/getall/paginated?page=${page}&size=${pageSize}`;

        // If searching, use search endpoint with pagination
        if (keyword && keyword.length > 0) {
            url = `${baseUrl}/search/paginated/${keyword}?page=${page}&size=${pageSize}`;
        }

        $.ajax({
            url: url,
            type: "GET",
            success: function(response) {
                jobs = response.content;
                totalPages = response.totalPages;
                renderJobsTable(jobs);
                renderPaginationControls();
            },
            error: function(xhr, status, error) {
                console.error("Error loading jobs:", error);
                alert("Failed to load jobs. Please try again.");
            }
        });
    }

    // Render jobs in the table
    function renderJobsTable(jobsToRender) {
        const tableBody = $("#jobsTableBody");
        tableBody.empty();

        if (jobsToRender.length === 0) {
            tableBody.append(`
                <tr>
                    <td colspan="7" class="text-center">No jobs found</td>
                </tr>
            `);
            return;
        }

        jobsToRender.forEach((job, index) => {
            const row = `
                <tr>
                    <td>${(currentPage * pageSize) + index + 1}</td>
                    <td>${job.jobTitle}</td>
                    <td>${job.company}</td>
                    <td>${job.location}</td>
                    <td>${job.type}</td>
                    <td>
                        <span class="badge ${job.status === 'Active' ? 'bg-success' : 'bg-secondary'}">
                            ${job.status}
                        </span>
                    </td>
                    <td>
                        <button class="btn btn-sm btn-outline-primary edit-job" data-id="${job.id}">
                            <i class="fas fa-edit"></i> Edit
                        </button>
                        <button class="btn btn-sm btn-outline-danger delete-job" data-id="${job.id}">
                            <i class="fas fa-trash"></i> Delete
                        </button>
                        <button class="btn btn-sm ${job.status === 'Active' ? 'btn-warning' : 'btn-success'} toggle-status" data-id="${job.id}">
                            ${job.status === 'Active' ? 'Deactivate' : 'Activate'}
                        </button>
                    </td>
                </tr>
            `;
            tableBody.append(row);
        });

        // Attach event handlers to the new buttons
        attachEventHandlers();
    }

    // Render pagination controls
    function renderPaginationControls() {
        const pagination = $("#paginationControls");
        pagination.empty();

        if (totalPages <= 1) return;

        // Previous button
        pagination.append(`
            <li class="page-item ${currentPage === 0 ? 'disabled' : ''}">
                <a class="page-link" href="#" data-page="${currentPage - 1}">Previous</a>
            </li>
        `);

        // Page numbers - show up to 5 pages around current page
        let startPage = Math.max(0, currentPage - 2);
        let endPage = Math.min(totalPages - 1, currentPage + 2);

        // Adjust if we're at the beginning
        if (currentPage < 3) {
            endPage = Math.min(4, totalPages - 1);
        }
        // Adjust if we're at the end
        else if (currentPage > totalPages - 4) {
            startPage = Math.max(totalPages - 5, 0);
        }

        // First page and ellipsis if needed
        if (startPage > 0) {
            pagination.append(`
                <li class="page-item ${0 === currentPage ? 'active' : ''}">
                    <a class="page-link" href="#" data-page="0">1</a>
                </li>
            `);
            if (startPage > 1) {
                pagination.append('<li class="page-item disabled"><span class="page-link">...</span></li>');
            }
        }

        // Page numbers
        for (let i = startPage; i <= endPage; i++) {
            pagination.append(`
                <li class="page-item ${i === currentPage ? 'active' : ''}">
                    <a class="page-link" href="#" data-page="${i}">${i + 1}</a>
                </li>
            `);
        }

        // Last page and ellipsis if needed
        if (endPage < totalPages - 1) {
            if (endPage < totalPages - 2) {
                pagination.append('<li class="page-item disabled"><span class="page-link">...</span></li>');
            }
            pagination.append(`
                <li class="page-item ${totalPages - 1 === currentPage ? 'active' : ''}">
                    <a class="page-link" href="#" data-page="${totalPages - 1}">${totalPages}</a>
                </li>
            `);
        }

        // Next button
        pagination.append(`
            <li class="page-item ${currentPage === totalPages - 1 ? 'disabled' : ''}">
                <a class="page-link" href="#" data-page="${currentPage + 1}">Next</a>
            </li>
        `);
    }

    // Save new job
    $("#saveJobBtn").click(function() {
        const jobData = {
            jobTitle: $("#jobTitle").val(),
            company: $("#companyName").val(),
            location: $("#jobLocation").val(),
            type: $("#jobType").val(),
            jobDescription: $("#jobDescription").val(),
            status: "Active" // default status
        };

        $.ajax({
            url: baseUrl + "/create",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(jobData),
            success: function(response) {
                loadJobs(currentPage, currentKeyword); // Refresh the current page
                $("#addJobForm")[0].reset(); // Reset the form
                $("#addJobModal").modal("hide");
                alert("Job created successfully!");
            },
            error: function(xhr, status, error) {
                console.error("Error creating job:", error);
                alert("Failed to create job. Please try again.");
            }
        });
    });

    // Edit job - populate modal
    function populateEditModal(jobId) {
        const job = jobs.find(j => j.id == jobId);
        if (job) {
            $("#editJobId").val(job.id);
            $("#editJobTitle").val(job.jobTitle);
            $("#editCompanyName").val(job.company);
            $("#editJobLocation").val(job.location);
            $("#editJobType").val(job.type);
            $("#editJobDescription").val(job.jobDescription);

            $("#editJobModal").modal("show");
        }
    }

    // Update job
    $("#updateJobBtn").click(function() {
        const jobData = {
            id: $("#editJobId").val(),
            jobTitle: $("#editJobTitle").val(),
            company: $("#editCompanyName").val(),
            location: $("#editJobLocation").val(),
            type: $("#editJobType").val(),
            jobDescription: $("#editJobDescription").val(),
            status: "Active" // maintain status
        };

        $.ajax({
            url: baseUrl + "/update",
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify(jobData),
            success: function(response) {
                loadJobs(currentPage, currentKeyword); // Refresh the current page
                $("#editJobModal").modal("hide");
                alert("Job updated successfully!");
            },
            error: function(xhr, status, error) {
                console.error("Error updating job:", error);
                alert("Failed to update job. Please try again.");
            }
        });
    });

    // Toggle job status
    function toggleJobStatus(jobId) {
        $.ajax({
            url: baseUrl + "/changestatus/" + jobId,
            type: "PATCH",
            success: function(response) {
                loadJobs(currentPage, currentKeyword); // Refresh the current page
                alert("Job status updated successfully!");
            },
            error: function(xhr, status, error) {
                console.error("Error toggling job status:", error);
                alert("Failed to update job status. Please try again.");
            }
        });
    }

    // Search functionality
    $("#searchInput").on("keyup", function() {
        const keyword = $(this).val().trim();
        loadJobs(0, keyword); // Always reset to first page when searching
    });

    // Pagination click handler
    $(document).on('click', '.page-link', function(e) {
        e.preventDefault();
        const page = $(this).data('page');
        loadJobs(page, currentKeyword);
    });

    // Attach event handlers to dynamic elements
    function attachEventHandlers() {
        $(".edit-job").click(function() {
            const jobId = $(this).data("id");
            populateEditModal(jobId);
        });

        $(".toggle-status").click(function() {
            const jobId = $(this).data("id");
            toggleJobStatus(jobId);
        });

        $(".delete-job").click(function() {
            const jobId = $(this).data("id");
            if (confirm("Are you sure you want to delete this job?")) {
                $.ajax({
                    url: baseUrl + "/delete/" + jobId,
                    type: "DELETE",
                    success: function(response) {
                        // If we're on the last page and it's now empty, go to previous page
                        if (jobs.length === 1 && currentPage > 0) {
                            loadJobs(currentPage - 1, currentKeyword);
                        } else {
                            loadJobs(currentPage, currentKeyword);
                        }
                        alert("Job deleted successfully!");
                    },
                    error: function(xhr, status, error) {
                        console.error("Error deleting job:", error);
                        alert("Failed to delete job. Please try again.");
                    }
                });
            }
        });
    }
});