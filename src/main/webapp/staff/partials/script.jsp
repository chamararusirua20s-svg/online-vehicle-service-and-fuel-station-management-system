<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script>
    function confirmDelete(url) {
      if (confirm('Are you sure you want to delete this record?')) {
        window.location.href = url;
      }
      return false;
    }

    document.getElementById('search').addEventListener('input', function () {
      const query = this.value.toLowerCase();
      document.querySelectorAll('#table tr').forEach(row => {
        row.style.display = row.innerText.toLowerCase().includes(query) ? '' : 'none';
      });
    });
</script>