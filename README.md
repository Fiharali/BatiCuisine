# BatiCuisine

## Description
**BatiCuisine** is a Java-based application designed for construction and kitchen renovation professionals. It provides an accurate estimation of total project costs, including materials, labor, taxes, and transportation costs. The application also supports client management, the creation of quotes, and a comprehensive financial overview of kitchen renovation projects.

## Features

### 1. Project Management
- Create new construction or renovation projects.
- Manage project details such as name, total cost, profit margin, and project status.
- Associate clients with each project.

### 2. Component Management
- Manage both material and labor components of the project.
- Materials:
  - Track costs, quantities, and types (e.g., materials, labor).
  - Account for taxes, quality coefficient, and transportation costs.
- Labor:
  - Track hours worked, hourly rates, and productivity levels.

### 3. Client Management
- Record client details (name, address, phone number).
- Differentiate between professional and individual clients.
- Calculate discounts and apply taxes based on the client type.

### 4. Quote Generation
- Generate project quotes with estimated costs for materials, labor, and equipment.
- Include issuance and validity dates in quotes.
- Track whether a quote is accepted or rejected by the client.

### 5. Cost Calculation
- Integrate material and labor costs into the project total.
- Apply profit margins and taxes to calculate the final project cost.
- Adjust costs based on material quality or labor productivity.

### 6. Detailed Reporting
- Display project details, including client information, components, and the total cost.
- Provide a detailed breakdown of labor, materials, equipment, and taxes.

## User Stories
1. As a professional, I can create a new project to track all project details and associated costs.
2. As a user, I can add materials and labor to a project, including unit costs and quantities.
3. As a professional, I can generate a quote based on project costs to provide to the client.
4. As a client, I can accept or reject a quote to move forward with the project.
5. As a project manager, I can adjust costs based on the quality of materials or worker productivity.

## Technologies Used
- **Java** (Core language)
- **PostgreSQL** (Database)
- **JDBC** (Database connectivity)
- **Design Patterns**: Singleton, Repository Pattern
- **Java Time API** (Date management)
- **Streams & Collections** (Data processing)

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/baticuisine.git
