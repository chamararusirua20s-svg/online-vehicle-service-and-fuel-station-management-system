<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" class="dark">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AutoCare | Edit Fuel Log</title>
  <link rel="shortcut icon" href="https://ui-avatars.com/api/?name=${sessionScope.name}&background=0f172a&color=22c55e&rounded=true" type="image/x-icon">
  <script src="https://cdn.tailwindcss.com"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<body class="bg-slate-900 text-gray-100 min-h-screen flex">

<%@ include file="../partials/sidebar.jsp" %>

<div class="flex-1 flex flex-col md:ml-60">
  <%@ include file="../partials/header.jsp" %>

  <main class="flex-1 p-6 space-y-6">
    <form action="${pageContext.request.contextPath}/staff/fuel-log" method="POST" class="bg-slate-800 shadow-lg rounded-lg p-8 w-full">
      <input type="hidden" name="action" value="update">
      <input type="hidden" name="id" value="${log.logId}">

      <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
        <!-- Left -->
        <div class="space-y-4">
          <div>
            <label for="vehicleId" class="block text-sm font-medium mb-1">Vehicle</label>
            <select name="vehicleId" id="vehicleId" required
              class="w-full px-4 py-2 rounded-lg border border-slate-600 bg-slate-700 text-white focus:ring-green-500">
              <option value="">-- Select Vehicle --</option>
              <c:forEach var="vehicle" items="${vehicles}">
                <option value="${vehicle.vehicleId}" <c:if test="${vehicle.plateNumber == log.plateNumber}">selected</c:if>>
                  ${vehicle.plateNumber} - ${vehicle.brand}
                </option>
              </c:forEach>
            </select>
          </div>

          <div>
            <label for="fuelType" class="block text-sm font-medium mb-1">Fuel Type</label>
            <select name="fuelType" id="fuelType" required
              class="w-full px-4 py-2 rounded-lg border border-slate-600 bg-slate-700 text-white focus:ring-green-500">
              <option value="Petrol" <c:if test="${log.fuelType == 'Petrol'}">selected</c:if>>Petrol</option>
              <option value="Diesel" <c:if test="${log.fuelType == 'Diesel'}">selected</c:if>>Diesel</option>
            </select>
          </div>
        </div>

        <!-- Right -->
        <div class="space-y-4">
          <div>
            <label for="liters" class="block text-sm font-medium mb-1">Liters</label>
            <input type="number" name="liters" id="liters" step="0.1" min="0.1" required
              value="${log.liters}"
              class="w-full px-4 py-2 rounded-lg border border-slate-600 bg-slate-700 text-white focus:ring-green-500">
          </div>
        </div>
      </div>

      <!-- Submit -->
      <div class="mt-8 flex justify-end">
        <button type="submit" class="flex items-center gap-2 px-6 py-2 bg-green-600 hover:bg-green-700 text-white rounded-lg font-semibold transition">
          <i class="fas fa-save"></i> Update Log
        </button>
      </div>
    </form>
  </main>
</div>

<%@ include file="../partials/script.jsp" %>
</body>
</html>