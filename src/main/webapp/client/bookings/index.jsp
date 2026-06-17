<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en" class="dark">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AutoCare | My Bookings</title>
  <link rel="shortcut icon" href="https://ui-avatars.com/api/?name=AutoCare&background=0f172a&color=22c55e&rounded=true" type="image/x-icon">
  <script src="https://cdn.tailwindcss.com"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>

<body class="bg-slate-900 text-gray-100 min-h-screen flex flex-col">

  <!-- Top Navbar -->
  <%@ include file="../partials/header.jsp" %>

  <!-- Main Content -->
  <main class="flex-1 p-6 max-w-7xl mx-auto w-full space-y-6">

    <!-- Title -->
    <div class="flex items-center justify-between">
      <h2 class="text-2xl font-semibold text-white">
        <i class="fas fa-calendar-check mr-2 text-green-400"></i> My Bookings
      </h2>
    </div>

    <!-- Table -->
    <div class="overflow-x-auto bg-slate-800 shadow-lg rounded-lg">
      <table class="w-full text-sm text-left">
        <thead class="text-xs uppercase bg-slate-700 text-gray-300">
          <tr>
            <th class="px-6 py-4">Booking ID</th>
            <th class="px-6 py-4">Plate Number</th>
            <th class="px-6 py-4">Brand</th>
            <th class="px-6 py-4">Service Type</th>
            <th class="px-6 py-4">Date</th>
            <th class="px-6 py-4">Status</th>
            <th class="px-6 py-4 text-center">Actions</th>
          </tr>
        </thead>
        <tbody class="text-gray-300">
          <c:choose>
            <c:when test="${not empty bookings}">
              <c:forEach var="booking" items="${bookings}">
                <tr class="border-b border-slate-700 hover:bg-slate-700/50">
                  <td class="px-6 py-4">${booking.bookingId}</td>
                  <td class="px-6 py-4">${booking.plateNumber}</td>
                  <td class="px-6 py-4">${booking.brand}</td>
                  <td class="px-6 py-4">${booking.serviceType}</td>
                  <td class="px-6 py-4">${booking.bookingDate}</td>
                  <td class="px-6 py-4">
                    <span class="inline-block px-3 py-1 rounded-full text-xs font-semibold
                      <c:choose>
                        <c:when test="${booking.status == 'Pending'}">bg-yellow-500/20 text-yellow-400</c:when>
                        <c:when test="${booking.status == 'In Progress'}">bg-blue-500/20 text-blue-400</c:when>
                        <c:when test="${booking.status == 'Completed'}">bg-green-500/20 text-green-400</c:when>
                        <c:otherwise>bg-gray-500/20 text-gray-300</c:otherwise>
                      </c:choose>">
                      ${booking.status}
                    </span>
                  </td>
                  <td class="px-6 py-4 text-center space-x-3">
                    <a href="${pageContext.request.contextPath}/client/my-bookings?action=cancel&id=${booking.bookingId}"
					   onclick="return confirm('Are you sure you want to cancel this booking?');"
					   class="text-red-400 hover:text-red-500">
					   <i class="fas fa-times-circle"></i> Cancel
					</a>
                  </td>
                </tr>
              </c:forEach>
            </c:when>
            <c:otherwise>
              <tr>
                <td colspan="6" class="px-6 py-4 text-center text-gray-400">No bookings found.</td>
              </tr>
            </c:otherwise>
          </c:choose>
        </tbody>
      </table>
    </div>

  </main>

</body>
</html>