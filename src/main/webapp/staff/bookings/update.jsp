<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" class="dark">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AutoCare | Update Booking Status</title>
  <link rel="shortcut icon" href="https://ui-avatars.com/api/?name=AutoCare&background=0f172a&color=22c55e&rounded=true" type="image/x-icon">
  <script src="https://cdn.tailwindcss.com"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<body class="bg-slate-900 text-gray-100 min-h-screen flex">

<%@ include file="../partials/sidebar.jsp" %>

<div class="flex-1 flex flex-col md:ml-60">
  <%@ include file="../partials/header.jsp" %>

  <main class="flex-1 p-6 space-y-6">
    <form action="${pageContext.request.contextPath}/staff/booking" method="POST" class="bg-slate-800 shadow-lg rounded-lg p-8 w-full">
      <input type="hidden" name="action" value="update">
      <input type="hidden" name="id" value="${booking.bookingId}">

      <div class="space-y-4">
        <div>
          <label class="block text-sm font-medium mb-1" for="status">Booking Status</label>
          <select name="status" id="status" required
            class="w-full px-4 py-2 rounded-lg border border-slate-600 bg-slate-700 text-white focus:ring-green-500">
            <option value="Pending" <c:if test="${booking.status == 'Pending'}">selected</c:if>>Pending</option>
            <option value="In Progress" <c:if test="${booking.status == 'In Progress'}">selected</c:if>>In Progress</option>
            <option value="Completed" <c:if test="${booking.status == 'Completed'}">selected</c:if>>Completed</option>
          </select>
        </div>
      </div>

      <div class="mt-8 flex justify-end">
        <button type="submit" class="flex items-center gap-2 px-6 py-2 bg-green-600 hover:bg-green-700 text-white rounded-lg font-semibold transition">
          <i class="fas fa-save"></i> Update Status
        </button>
      </div>
    </form>
  </main>
</div>

<%@ include file="../partials/script.jsp" %>
</body>
</html>