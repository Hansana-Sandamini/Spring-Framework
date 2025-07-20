$(document).ready(function() {
    // Global variables
    let jobs = [];
    const baseUrl = "http://localhost:8080/api/v1/job";
    let currentPage = 0;
    const pageSize = 10;
    let totalPages = 0;
    let currentKeyword = '';

    // Initialize the page
    loadJobs(currentPage);

    // Helper function for SweetAlert notifications
    function showAlert(icon, title, text) {
        Swal.fire({
            icon: icon,
            title: title,
            text: text,
            toast: true,
            position: 'middle',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true,
        });
    }

    // Helper function for confirmation dialogs
    async function showConfirm(title, text, confirmButtonText = 'Yes') {
        const result = await Swal.fire({
            title: title,
            text: text,
            icon: 'question',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: confirmButtonText,
            cancelButtonText: 'Cancel'
        });
        return result.isConfirmed;
    }

    // Load jobs from backend with pagination
    function loadJobs(page = 0, keyword = '') {
        currentPage = page;
        currentKeyword = keyword;

        let url = `${baseUrl}/getall/paginated?page=${page}&size=${pageSize}`;

        // If searching, use search endpoint with pagination
        if (keyword && keyword.length > 0) {
            url = `${baseUrl}/search/paginated/${encodeURIComponent(keyword)}?page=${page}&size=${pageSize}`;
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
                if (xhr.status === 404) {
                    // Handle no jobs found case
                    jobs = [];
                    renderJobsTable([]);
                    renderPaginationControls();
                    showAlert('info', 'No Jobs', 'No jobs found matching your criteria');
                } else {
                    showAlert('error', 'Error', 'Failed to load jobs. Please try again.');
                }
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
        const jobTitle = $("#jobTitle").val().trim();
        const company = $("#companyName").val().trim();
        const location = $("#jobLocation").val().trim();

        if (!jobTitle || !company || !location) {
            showAlert('warning', 'Validation Error', 'Please fill in all required fields');
            return;
        }

        const jobData = {
            jobTitle: jobTitle,
            company: company,
            location: location,
            type: $("#jobType").val(),
            jobDescription: $("#jobDescription").val(),
            status: "Active"
        };

        $.ajax({
            url: baseUrl + "/create",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(jobData),
            success: function(response) {
                loadJobs(0); // Always go to first page after creating
                $("#addJobForm")[0].reset();
                $("#addJobModal").modal("hide");
                showAlert('success', 'Success', 'Job created successfully!');
            },
            error: function(xhr, status, error) {
                console.error("Error creating job:", error);
                showAlert('error', 'Error', xhr.responseJSON?.message || 'Failed to create job. Please try again.');
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
        const jobTitle = $("#editJobTitle").val().trim();
        const company = $("#editCompanyName").val().trim();
        const location = $("#editJobLocation").val().trim();

        if (!jobTitle || !company || !location) {
            showAlert('warning', 'Validation Error', 'Please fill in all required fields');
            return;
        }

        const jobData = {
            id: $("#editJobId").val(),
            jobTitle: jobTitle,
            company: company,
            location: location,
            type: $("#editJobType").val(),
            jobDescription: $("#editJobDescription").val(),
            status: "Active"
        };

        $.ajax({
            url: baseUrl + "/update",
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify(jobData),
            success: function(response) {
                loadJobs(currentPage, currentKeyword);
                $("#editJobModal").modal("hide");
                showAlert('success', 'Success', 'Job updated successfully!');
            },
            error: function(xhr, status, error) {
                console.error("Error updating job:", error);
                showAlert('error', 'Error', xhr.responseJSON?.message || 'Failed to update job. Please try again.');
            }
        });
    });

    // Toggle job status
    async function toggleJobStatus(jobId) {
        const job = jobs.find(j => j.id == jobId);
        const action = job.status === 'Active' ? 'deactivate' : 'activate';

        const isConfirmed = await showConfirm(
            'Are you sure?',
            `Do you want to ${action} this job?`,
            job.status === 'Active' ? 'Deactivate' : 'Activate'
        );

        if (!isConfirmed) return;

        $.ajax({
            url: baseUrl + "/changestatus/" + jobId,
            type: "PATCH",
            success: function(response) {
                loadJobs(currentPage, currentKeyword);
                showAlert('success', 'Success', `Job ${action}d successfully!`);
            },
            error: function(xhr, status, error) {
                console.error("Error toggling job status:", error);
                showAlert('error', 'Error', xhr.responseJSON?.message || `Failed to ${action} job. Please try again.`);
            }
        });
    }

    // Search functionality
    $("#searchInput").on("keyup", function() {
        const keyword = $(this).val().trim();
        loadJobs(0, keyword); // Reset to first page when searching
    });

    // Pagination click handler
    $(document).on('click', '.page-link', function(e) {
        e.preventDefault();
        const page = $(this).data('page');
        if (page >= 0 && page < totalPages) {
            loadJobs(page, currentKeyword);
        }
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

        $(".delete-job").click(async function() {
            const jobId = $(this).data("id");
            const isConfirmed = await showConfirm(
                'Are you sure?',
                'This will permanently delete the job. This action cannot be undone.',
                'Delete'
            );

            if (!isConfirmed) return;

            $.ajax({
                url: baseUrl + "/delete/" + jobId,
                type: "DELETE",
                success: function(response) {
                    // Handle empty last page case
                    if (jobs.length === 1 && currentPage > 0) {
                        loadJobs(currentPage - 1, currentKeyword);
                    } else {
                        loadJobs(currentPage, currentKeyword);
                    }
                    showAlert('success', 'Success', 'Job deleted successfully!');
                },
                error: function(xhr, status, error) {
                    console.error("Error deleting job:", error);
                    showAlert('error', 'Error', xhr.responseJSON?.message || 'Failed to delete job. Please try again.');
                }
            });
        });
    }
});
