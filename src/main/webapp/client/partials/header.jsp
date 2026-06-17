<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<header class="bg-slate-800 shadow-sm w-full z-10">
  <div class="max-w-7xl mx-auto flex justify-between items-center px-4 py-4">

    <!-- Left: Brand -->
    <h1 class="text-xl font-bold text-green-400">AutoCare</h1>

    <!-- Center: Navigation -->
    <nav class="space-x-6 text-sm font-medium">
      <a href="${pageContext.request.contextPath}/client/vehicle" class="text-gray-300 hover:text-white transition">
        <i class="fas fa-car mr-1"></i> My Vehicles
      </a>
      <a href="${pageContext.request.contextPath}/client/my-bookings" class="text-gray-300 hover:text-white transition">
        <i class="fas fa-calendar-check mr-1"></i> My Bookings
      </a>
      <a href="${pageContext.request.contextPath}/client/profile" class="text-gray-300 hover:text-white transition">
        <i class="fas fa-user-circle mr-1"></i> Profile
      </a>
    </nav>

    <!-- Right: User Info -->
    <div class="flex items-center space-x-4">
      <div class="text-right text-sm">
        <p class="text-white">${sessionScope.name}</p>
        <p class="text-gray-400 text-xs">${sessionScope.email}</p>
      </div>
      <img src="https://ui-avatars.com/api/?name=${sessionScope.name}&background=22c55e&color=fff"
           alt="Profile" class="w-10 h-10 rounded-full shadow-md" />
      <a href="${pageContext.request.contextPath}/client/logout"
         class="text-red-400 hover:text-red-500 text-sm font-semibold">
        <i class="fas fa-sign-out-alt mr-1"></i> Logout
      </a>
    </div>
  </div>
</header>