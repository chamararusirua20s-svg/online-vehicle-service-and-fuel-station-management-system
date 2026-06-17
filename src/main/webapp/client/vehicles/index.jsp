<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en" class="dark">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AutoCare | My Vehicles</title>
  <link rel="shortcut icon" href="https://ui-avatars.com/api/?name=AutoCare&background=0f172a&color=22c55e&rounded=true" type="image/x-icon">
  <script src="https://cdn.tailwindcss.com"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>

<body class="bg-slate-900 text-gray-100 min-h-screen flex flex-col">

  <!-- Top Navbar -->
  <%@ include file="../partials/header.jsp" %>

  <!-- Main Content -->
  <main class="flex-1 p-6 max-w-7xl mx-auto w-full space-y-6">

    <!-- Title + Add -->
    <div class="flex items-center justify-between">
      <h2 class="text-2xl font-semibold text-white">
        <i class="fas fa-car mr-2 text-green-400"></i> My Vehicles
      </h2>
      <a href="${pageContext.request.contextPath}/client/vehicle?action=create"
         class="bg-green-600 text-white px-4 py-2 rounded-lg hover:bg-green-700 transition text-sm font-medium">
        <i class="fas fa-plus mr-1"></i> Add Vehicle
      </a>
    </div>

    <!-- Table -->
    <div class="overflow-x-auto bg-slate-800 shadow-lg rounded-lg">
      <table class="w-full text-sm text-left">
        <thead class="text-xs uppercase bg-slate-700 text-gray-300">
          <tr>
            <th class="px-6 py-4">ID</th>
            <th class="px-6 py-4">Plate Number</th>
            <th class="px-6 py-4">Model</th>
            <th class="px-6 py-4">Brand</th>
            <th class="px-6 py-4">Year</th>
            <th class="px-6 py-4 text-center">Actions</th>
          </tr>
        </thead>
        <tbody class="text-gray-300">
          <c:choose>
            <c:when test="${not empty vehicles}">
              <c:forEach var="vehicle" items="${vehicles}">
                <tr class="border-b border-slate-700 hover:bg-slate-700/50">
                  <td class="px-6 py-4">${vehicle.vehicleId}</td>
                  <td class="px-6 py-4">${vehicle.plateNumber}</td>
                  <td class="px-6 py-4">${vehicle.model}</td>
                  <td class="px-6 py-4">${vehicle.brand}</td>
                  <td class="px-6 py-4">${vehicle.year}</td>
                  <td class="px-6 py-4 text-center space-x-3">
                    <a href="${pageContext.request.contextPath}/client/vehicle?action=edit&id=${vehicle.vehicleId}"
                       class="text-green-400 hover:text-green-500">
                      <i class="fas fa-edit"></i>
                    </a>
                    <a href="${pageContext.request.contextPath}/client/vehicle?action=delete&id=${vehicle.vehicleId}"
                       onclick="return confirm('Are you sure you want to delete this vehicle?');"
                       class="text-red-400 hover:text-red-500">
                      <i class="fas fa-trash"></i>
                    </a>
                    <!-- Book Service -->
				    <a href="${pageContext.request.contextPath}/client/book-service?vehicleId=${vehicle.vehicleId}"
				       class="text-cyan-400 hover:text-cyan-500" title="Book Service">
				      <i class="fas fa-tools"></i>
				    </a>
                  </td>
                </tr>
              </c:forEach>
            </c:when>
            <c:otherwise>
              <tr>
                <td colspan="6" class="px-6 py-4 text-center text-gray-400">No vehicles found.</td>
              </tr>
            </c:otherwise>
          </c:choose>
        </tbody>
      </table>
    </div>

  </main>

</body>
</html>