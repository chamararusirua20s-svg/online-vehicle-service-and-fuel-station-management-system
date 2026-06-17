<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<aside class="fixed top-0 left-0 w-60 hidden md:flex flex-col bg-slate-800 border-r border-slate-700 shadow-md h-screen z-20">
  <!-- Logo -->
  <div class="p-6 text-center border-b border-slate-700">
    <h1 class="text-2xl font-bold text-green-400">AutoCare</h1>
  </div>

  <!-- Navigation -->
  <nav class="flex-1 px-4 py-6 space-y-2 overflow-y-auto">
    <a href="${pageContext.request.contextPath}/staff/vehicle"
      class="flex items-center p-3 rounded hover:bg-green-600/20 transition text-gray-300 hover:text-white">
      <i class="fas fa-car mr-3"></i> Vehicles
    </a>

    <a href="${pageContext.request.contextPath}/staff/booking"
      class="flex items-center p-3 rounded hover:bg-green-600/20 transition text-gray-300 hover:text-white">
      <i class="fas fa-tools mr-3"></i> Service Bookings
    </a>

    <a href="${pageContext.request.contextPath}/staff/fuel-log"
      class="flex items-center p-3 rounded hover:bg-green-600/20 transition text-gray-300 hover:text-white">
      <i class="fas fa-gas-pump mr-3"></i> Fuel Logs
    </a>

    <a href="${pageContext.request.contextPath}/staff/staff"
      class="flex items-center p-3 rounded hover:bg-green-600/20 transition text-gray-300 hover:text-white">
      <i class="fas fa-user-cog mr-3"></i> Staffs
    </a>
    
    <a href="${pageContext.request.contextPath}/staff/user"
      class="flex items-center p-3 rounded hover:bg-green-600/20 transition text-gray-300 hover:text-white">
      <i class="fas fa-user-group mr-3"></i> Users
    </a>
  </nav>

  <!-- Logout -->
  <div class="p-4 border-t border-slate-700">
    <a href="${pageContext.request.contextPath}/staff/logout" class="w-full flex items-center justify-center text-red-400 hover:text-red-500 font-semibold transition">
      <i class="fas fa-sign-out-alt mr-2"></i> Logout
    </a>
  </div>
</aside>