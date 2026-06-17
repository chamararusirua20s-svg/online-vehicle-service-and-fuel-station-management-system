<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String success = (String) session.getAttribute("success");
    String error = (String) session.getAttribute("error");
    session.removeAttribute("success");
    session.removeAttribute("error");
%>

<!DOCTYPE html>
<html lang="en" class="dark">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>AutoCare | Book Service</title>
  <link rel="shortcut icon" href="https://ui-avatars.com/api/?name=AutoCare&background=0f172a&color=22c55e&rounded=true" type="image/x-icon">
  <script src="https://cdn.tailwindcss.com"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>

<body class="bg-slate-900 text-gray-100 min-h-screen flex flex-col">
  <%@ include file="../partials/header.jsp" %>

  <main class="flex-1 p-6 max-w-7xl mx-auto w-full">
    <h2 class="text-3xl font-bold text-green-400 mb-6">
      <i class="fas fa-tools mr-2"></i> Book a Service
    </h2>

    <!-- Flash messages -->
    <c:if test="${not empty success}">
      <div class="bg-green-600/20 border border-green-600 text-green-400 px-4 py-3 rounded-lg mb-4">
        <i class="fas fa-check-circle mr-2"></i> ${success}
      </div>
    </c:if>
    <c:if test="${not empty error}">
      <div class="bg-red-600/20 border border-red-600 text-red-400 px-4 py-3 rounded-lg mb-4">
        <i class="fas fa-exclamation-circle mr-2"></i> ${error}
      </div>
    </c:if>

    <div class="bg-slate-800 rounded-2xl shadow-lg p-8">
      <form action="${pageContext.request.contextPath}/client/book-service" method="POST" class="grid grid-cols-1 md:grid-cols-2 gap-6">
        <input type="hidden" name="vehicle_id" value="${vehicle.vehicleId}" />

        <!-- Plate Number (read-only) -->
        <div>
          <label class="block mb-1 text-gray-300 font-medium">Plate Number</label>
          <input type="text" readonly value="${vehicle.plateNumber}"
            class="w-full px-4 py-2 bg-slate-700 border border-slate-600 rounded-lg text-white">
        </div>

        <!-- Model (read-only) -->
        <div>
          <label class="block mb-1 text-gray-300 font-medium">Model</label>
          <input type="text" readonly value="${vehicle.model}"
            class="w-full px-4 py-2 bg-slate-700 border border-slate-600 rounded-lg text-white">
        </div>

        <!-- Service Type -->
        <div>
          <label class="block mb-1 text-gray-300 font-medium">Service Type</label>
          <select name="service_type" required
            class="w-full px-4 py-2 bg-slate-700 border border-slate-600 rounded-lg text-white focus:ring-2 focus:ring-green-500">
            <option value="">Select Service</option>
            <option value="Oil Change">Oil Change</option>
            <option value="Full Inspection">Full Inspection</option>
            <option value="Engine Diagnosis">Engine Diagnosis</option>
            <option value="Battery Check">Battery Check</option>
          </select>
        </div>

        <!-- Booking Date -->
        <div>
          <label class="block mb-1 text-gray-300 font-medium">Booking Date</label>
          <input type="date" name="booking_date" required min="<%= java.time.LocalDate.now() %>"
            class="w-full px-4 py-2 bg-slate-700 border border-slate-600 rounded-lg text-white focus:ring-2 focus:ring-green-500">
        </div>

        <!-- Buttons -->
        <div class="md:col-span-2 flex justify-between mt-6">
          <a href="${pageContext.request.contextPath}/client/vehicle"
            class="bg-gray-600 text-white px-6 py-2 rounded-lg hover:bg-gray-700 transition">
            <i class="fas fa-arrow-left mr-1"></i> Back
          </a>
          <button type="submit"
            class="bg-green-600 text-white px-6 py-2 rounded-lg hover:bg-green-700 transition">
            <i class="fas fa-paper-plane mr-1"></i> Confirm Booking
          </button>
        </div>
      </form>
    </div>
  </main>
</body>
</html>