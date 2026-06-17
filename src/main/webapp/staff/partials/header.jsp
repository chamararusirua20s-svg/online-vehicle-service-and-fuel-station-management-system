<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    String uri = request.getRequestURI();
    String pageTitle = "Dashboard Overview";

    if (uri.contains("/staff/staff")) {
        pageTitle = "Staff Management";
    } else if (uri.contains("/staff/vehicle")) {
        pageTitle = "Vehicles Management";
    } else if (uri.contains("/staff/booking")) {
        pageTitle = "Service Bookings";
    } else if (uri.contains("/staff/fuel-log")) {
        pageTitle = "Fuel Logs";
    } else if (uri.contains("/admin/user")) {
        pageTitle = "Users Management";
    }
    request.setAttribute("pageTitle", pageTitle);
%>

<header class="flex justify-between items-center p-4 bg-slate-800 shadow-sm">
    <h2 class="text-xl font-bold text-white">${pageTitle}</h2>
    <div class="flex items-center space-x-4">
        <div class="text-right text-sm">
            <p class="text-white">${sessionScope.name}</p>
            <p class="text-gray-400 text-xs">${sessionScope.email}</p>
        </div>
        <img
            src="https://ui-avatars.com/api/?name=${sessionScope.name}&background=22c55e&color=fff"
            alt="Profile" class="w-10 h-10 rounded-full shadow-md">
    </div>
</header>