// scripts.js

// Adjust table columns to fit better
document.addEventListener("DOMContentLoaded", function() {
    const tables = document.querySelectorAll("table");
    tables.forEach(table => {
        table.classList.add("table", "table-striped", "table-bordered");
    });
});
