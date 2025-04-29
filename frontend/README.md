# Sorting Algorithm Visualizer

A full-stack application that visualizes sorting algorithms in real-time. Built with Spring Boot (backend) and React (frontend), connected via WebSockets.

## Features

- Real-time visualization of sorting algorithms
- Supports multiple sorting algorithms:
  - Bubble Sort
  - Selection Sort
  - Insertion Sort
  - Quick Sort
  - Merge Sort
- Adjustable array size and animation speed
- Step-by-step visualization with color coding
- Statistics tracking (comparisons and swaps)

## Tech Stack

- **Backend**: Spring Boot, WebSocket
- **Frontend**: React, Native WebSocket API
- **Build Tools**: Maven/Gradle (backend), npm (frontend)

## Prerequisites

- Java 17 or higher
- Node.js 14 or higher and npm
- Maven (for building the Spring Boot application)

## Project Structure

```
algorithm-visualizer/
├── backend/                # Spring Boot application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/algorithmvisualizer/
│   │   │   │   ├── config/           # WebSocket configuration
│   │   │   │   ├── handler/          # WebSocket handler
│   │   │   │   ├── model/            # Data models
│   │   │   │   ├── service/          # Business logic and algorithm implementations
│   │   │   │   └── AlgorithmVisualizerApplication.java  # Main class
│   │   │   └── resources/            # Application properties and static resources
│   │   └── test/                     # Unit and integration tests
│   └── pom.xml                       # Maven dependencies
└── frontend/               # React application
    ├── public/
    ├── src/
    │   ├── components/     # React components
    │   ├── App.js          # Main React component
    │   └── index.js        # Entry point
    ├── package.json        # npm dependencies
    └── README.md           # Frontend documentation
```

## Setup and Running

### Backend (Spring Boot)

1. Navigate to the backend directory:

   ```bash
   cd algorithm-visualizer/backend
   ```

2. If using Maven, build the project:

   ```bash
   mvn clean install
   ```

3. Run the Spring Boot application:

   ```bash
   mvn spring-boot:run
   ```

   The backend will start on `http://localhost:8080`.

### Frontend (React)

1. Navigate to the frontend directory:

   ```bash
   cd algorithm-visualizer/frontend
   ```

2. Install dependencies:

   ```bash
   npm install
   ```

3. Start the development server:

   ```bash
   npm start
   ```

   The frontend will be available at `http://localhost:3000`.

## Usage

1. Open your browser and navigate to `http://localhost:3000`
2. Select a sorting algorithm from the dropdown
3. Adjust the array size and animation speed as desired
4. Click "Start Sorting" to begin the visualization
5. Watch as the algorithm sorts the array in real-time
6. View statistics and current step information at the bottom of the page

## Color Coding

- **Blue**: Default bar color
- **Orange**: Current index being processed
- **Pink**: Index being compared
- **Green**: Sorted array (when complete)

## Troubleshooting

### Common Issues:

1. **Backend Connection Failed**:

   - Ensure the Spring Boot application is running on port 8080
   - Check for any CORS issues (the backend is configured to allow requests from `http://localhost:3000`)

2. **WebSocket Connection Issues**:

   - Check browser console for WebSocket errors
   - Ensure no firewall or proxy is blocking WebSocket connections

3. **React Build Errors**:
   - Make sure you have the correct versions of Node.js and npm installed
   - Try running `npm clean-install` to clear the cache and reinstall dependencies

## Extending the Application

### Customizing the Visualization:

1. Modify `BarChart.js` and `BarChart.css` to change the appearance of the bars
2. Update color coding in the `getBarColor` function in `BarChart.js`

## License

This project is open-source and available under the MIT License.

## Acknowledgements

- Spring Boot for the backend framework
- React for the frontend library
- Native WebSocket API for real-time communication
