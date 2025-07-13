$(document).ready(function() {
    // Global variables
    let jobs = [];
    const baseUrl = "http://localhost:8080/api/v1/job";

    // Initialize the page
    loadJobs();

    // Load all jobs from backend
    function loadJobs() {
        $.ajax({
            url: baseUrl + "/getall",
            type: "GET",
            success: function(response) {
                jobs = response;
                renderJobsTable(jobs);
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

        jobsToRender.forEach((job, index) => {
            const row = `
                <tr>
                    <td>${index + 1}</td>
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
                loadJobs(); // Refresh the table
                $("#addJobForm")[0].reset(); // Reset the form
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
                loadJobs(); // Refresh the table
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
                loadJobs(); // Refresh the table
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

        if (keyword.length > 0) {
            $.ajax({
                url: baseUrl + "/search/" + keyword,
                type: "GET",
                success: function(response) {
                    renderJobsTable(response);
                },
                error: function(xhr, status, error) {
                    console.error("Error searching jobs:", error);
                    // Fallback to client-side filtering if API fails
                    const filteredJobs = jobs.filter(job =>
                        job.jobTitle.toLowerCase().includes(keyword.toLowerCase()) ||
                        job.company.toLowerCase().includes(keyword.toLowerCase()) ||
                        job.location.toLowerCase().includes(keyword.toLowerCase())
                    );
                    renderJobsTable(filteredJobs);
                }
            });
        } else {
            // If search field is empty, show all jobs
            renderJobsTable(jobs);
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

        // Delete job
        $(".delete-job").click(function() {
            const jobId = $(this).data("id");
            if (confirm("Are you sure you want to delete this job?")) {
                $.ajax({
                    url: baseUrl + "/delete/" + jobId,
                    type: "DELETE",
                    success: function(response) {
                        loadJobs(); // Refresh the table
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
