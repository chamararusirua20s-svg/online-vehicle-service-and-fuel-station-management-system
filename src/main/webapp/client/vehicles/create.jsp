<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="dark">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>AutoCare | Add Vehicle</title>
  <link rel="shortcut icon" href="https://ui-avatars.com/api/?name=AutoCare&background=0f172a&color=22c55e&rounded=true" type="image/x-icon">
  <script src="https://cdn.tailwindcss.com"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<body class="bg-slate-900 text-gray-100 min-h-screen flex flex-col">

  <%@ include file="../partials/header.jsp" %>

  <main class="flex-1 p-6 max-w-7xl mx-auto w-full">
    <h2 class="text-3xl font-bold text-green-400 mb-6">
      <i class="fas fa-car mr-2"></i> Add New Vehicle
    </h2>

    <div class="bg-slate-800 rounded-2xl shadow-lg p-8">
      <form action="${pageContext.request.contextPath}/client/vehicle" method="POST" class="grid grid-cols-1 md:grid-cols-2 gap-6">
        <input type="hidden" name="action" value="create" />

        <div>
          <label class="block mb-1 text-gray-300 font-medium">Plate Number</label>
          <input type="text" name="plate_number" required
            class="w-full px-4 py-2 bg-slate-700 border border-slate-600 rounded-lg focus:ring-2 focus:ring-green-500 focus:outline-none text-white"
            placeholder="e.g., ABC-1234">
        </div>

        <div>
          <label class="block mb-1 text-gray-300 font-medium">Model</label>
          <input type="text" name="model" required
            class="w-full px-4 py-2 bg-slate-700 border border-slate-600 rounded-lg focus:ring-2 focus:ring-green-500 focus:outline-none text-white"
            placeholder="e.g., Corolla">
        </div>

        <div>
          <label class="block mb-1 text-gray-300 font-medium">Brand</label>
          <input type="text" name="brand" required
            class="w-full px-4 py-2 bg-slate-700 border border-slate-600 rounded-lg focus:ring-2 focus:ring-green-500 focus:outline-none text-white"
            placeholder="e.g., Toyota">
        </div>

        <div>
          <label class="block mb-1 text-gray-300 font-medium">Year</label>
          <input type="number" name="year" required min="1900" max="2099"
            class="w-full px-4 py-2 bg-slate-700 border border-slate-600 rounded-lg focus:ring-2 focus:ring-green-500 focus:outline-none text-white"
            placeholder="e.g., 2020">
        </div>

        <div class="md:col-span-2 flex justify-between mt-4">
          <a href="${pageContext.request.contextPath}/client/vehicle"
            class="bg-gray-600 text-white px-6 py-2 rounded-lg hover:bg-gray-700 transition">
            <i class="fas fa-arrow-left mr-1"></i> Cancel
          </a>
          <button type="submit"
            class="bg-green-600 text-white px-6 py-2 rounded-lg hover:bg-green-700 transition">
            <i class="fas fa-check-circle mr-1"></i> Save Vehicle
          </button>
        </div>
      </form>
    </div>
  </main>
</body>
</html>