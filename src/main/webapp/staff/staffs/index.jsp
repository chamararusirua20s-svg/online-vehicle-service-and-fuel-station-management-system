<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en" class="dark">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AutoCare | Staff Members</title>
  <link rel="shortcut icon" href="https://ui-avatars.com/api/?name=${sessionScope.name}&background=0f172a&color=22c55e&rounded=true" type="image/x-icon">

  <!-- Tailwind CSS -->
  <script src="https://cdn.tailwindcss.com"></script>
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>

<body class="bg-slate-900 text-gray-100 min-h-screen flex">

  <!-- Sidebar -->
  <%@ include file="../partials/sidebar.jsp" %>

  <!-- Main Content -->
  <div class="flex-1 flex flex-col md:ml-60">

    <!-- Header -->
    <%@ include file="../partials/header.jsp" %>

    <main class="flex-1 p-6 space-y-6">

      <!-- Actions -->
      <div class="flex flex-col md:flex-row md:justify-between md:items-center space-y-4 md:space-y-0">
        <a href="${pageContext.request.contextPath}/staff/staff?action=create"
          class="flex items-center gap-2 px-5 py-2 bg-green-600 hover:bg-green-700 text-white rounded-lg font-semibold transition">
          <i class="fas fa-plus"></i> Add Staff Member
        </a>

        <div class="relative w-full md:w-1/3">
          <input id="search" type="text" placeholder="Search staff..."
            class="w-full pl-10 pr-4 py-2 rounded-lg bg-slate-700 border border-slate-600 focus:ring-2 focus:ring-green-500 focus:outline-none text-white">
          <i class="fas fa-search absolute left-3 top-3 text-gray-400"></i>
        </div>
      </div>

      <!-- Table -->
      <div class="overflow-x-auto bg-slate-800 shadow-lg rounded-lg">
        <table class="w-full text-sm text-left">
          <thead class="text-xs uppercase bg-slate-700 text-gray-300">
            <tr>
              <th class="px-6 py-4">ID</th>
              <th class="px-6 py-4">Name</th>
              <th class="px-6 py-4">Email</th>
              <th class="px-6 py-4">Role</th>
              <th class="px-6 py-4 text-center">Actions</th>
            </tr>
          </thead>
          <tbody id="table" class="text-gray-300">
            <c:choose>
              <c:when test="${not empty staffList}">
                <c:forEach var="staff" items="${staffList}">
                  <tr class="border-b border-slate-700 hover:bg-slate-700/50">
                    <td class="px-6 py-4">${staff.staffId}</td>
                    <td class="px-6 py-4">${staff.name}</td>
                    <td class="px-6 py-4">${staff.email}</td>
                    <td class="px-6 py-4">${staff.role}</td>
                    <td class="px-6 py-4 text-center space-x-3">
                      <a href="${pageContext.request.contextPath}/staff/staff?action=edit&id=${staff.staffId}" class="text-green-400 hover:text-green-500">
                        <i class="fas fa-edit"></i>
                      </a>
                      <a href="${pageContext.request.contextPath}/staff/staff?action=delete&id=${staff.staffId}" onclick="return confirm('Are you sure you want to delete this staff member?');" class="text-red-400 hover:text-red-500">
                        <i class="fas fa-trash"></i>
                      </a>
                    </td>
                  </tr>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <tr>
                  <td colspan="5" class="px-6 py-4 text-center text-gray-400">No staff members found.</td>
                </tr>
              </c:otherwise>
            </c:choose>
          </tbody>
        </table>
      </div>

    </main>
  </div>

  <!-- Scripts -->
  <%@ include file="../partials/script.jsp" %>

</body>
</html>