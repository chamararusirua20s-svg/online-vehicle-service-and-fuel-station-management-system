<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en" class="dark">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>AutoCare | Edit Profile</title>
  <link rel="shortcut icon" href="https://ui-avatars.com/api/?name=AutoCare&background=0f172a&color=22c55e&rounded=true" type="image/x-icon">
  <script src="https://cdn.tailwindcss.com"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<body class="bg-slate-900 text-gray-100 min-h-screen flex flex-col">

  <%@ include file="../partials/header.jsp" %>

  <main class="flex-1 p-6 max-w-7xl mx-auto w-full">
    <h2 class="text-3xl font-bold text-green-400 mb-6">
      <i class="fas fa-user-cog mr-2"></i> Edit My Profile
    </h2>

    <!-- Flash Messages -->
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
      <form action="${pageContext.request.contextPath}/client/profile" method="POST" class="grid grid-cols-1 md:grid-cols-2 gap-6">

        <!-- Full Name -->
        <div>
          <label class="block mb-1 text-gray-300 font-medium">Full Name</label>
          <input type="text" name="full_name" required value="${userProfile.fullName}"
            class="w-full px-4 py-2 bg-slate-700 border border-slate-600 rounded-lg focus:ring-2 focus:ring-green-500 text-white">
        </div>

        <!-- Email -->
        <div>
          <label class="block mb-1 text-gray-300 font-medium">Email</label>
          <input type="email" name="email" required value="${userProfile.email}"
            class="w-full px-4 py-2 bg-slate-700 border border-slate-600 rounded-lg focus:ring-2 focus:ring-green-500 text-white">
        </div>

        <!-- Phone -->
        <div>
          <label class="block mb-1 text-gray-300 font-medium">Phone</label>
          <input type="text" name="phone" required value="${userProfile.phone}"
            class="w-full px-4 py-2 bg-slate-700 border border-slate-600 rounded-lg focus:ring-2 focus:ring-green-500 text-white">
        </div>

        <!-- Password -->
        <div>
          <label class="block mb-1 text-gray-300 font-medium">Password</label>
          <input type="password" name="password" id="password" required value="${userProfile.password}"
            class="w-full px-4 py-2 bg-slate-700 border border-slate-600 rounded-lg focus:ring-2 focus:ring-green-500 text-white">
        </div>

        <!-- Password Hints -->
        <div class="md:col-span-2">
          <p class="text-sm text-gray-400 mb-1">Password must meet the following:</p>
          <ul class="text-sm space-y-1 pl-5 list-disc">
            <li id="lengthHint" class="text-red-600">At least 8 characters</li>
            <li id="uppercaseHint" class="text-red-600">At least one uppercase letter</li>
            <li id="lowercaseHint" class="text-red-600">At least one lowercase letter</li>
            <li id="digitHint" class="text-red-600">At least one number</li>
          </ul>
        </div>

        <!-- Actions -->
        <div class="md:col-span-2 flex justify-between mt-4">
          <a href="${pageContext.request.contextPath}/client/vehicle"
            class="bg-gray-600 text-white px-6 py-2 rounded-lg hover:bg-gray-700 transition">
            <i class="fas fa-arrow-left mr-1"></i> Back
          </a>
          <button type="submit"
            class="bg-green-600 text-white px-6 py-2 rounded-lg hover:bg-green-700 transition">
            <i class="fas fa-save mr-1"></i> Update Profile
          </button>
        </div>
      </form>
    </div>
  </main>

  <!-- Password Hint Script -->
  <script>
    document.addEventListener("DOMContentLoaded", function () {
      const passwordInput = document.getElementById("password");

      const lengthHint = document.getElementById("lengthHint");
      const uppercaseHint = document.getElementById("uppercaseHint");
      const lowercaseHint = document.getElementById("lowercaseHint");
      const digitHint = document.getElementById("digitHint");

      function validatePasswordHints(val) {
        lengthHint.className = val.length >= 8 ? "text-green-600" : "text-red-600";
        uppercaseHint.className = /[A-Z]/.test(val) ? "text-green-600" : "text-red-600";
        lowercaseHint.className = /[a-z]/.test(val) ? "text-green-600" : "text-red-600";
        digitHint.className = /\d/.test(val) ? "text-green-600" : "text-red-600";
      }

      if (passwordInput) {
        validatePasswordHints(passwordInput.value); // Validate on load
        passwordInput.addEventListener("input", function () {
          validatePasswordHints(passwordInput.value); // Validate on input
        });
      }
    });
  </script>

</body>
</html>