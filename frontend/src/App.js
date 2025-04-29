import React from "react";
import "./styles/App.css";
import AlgorithmVisualizer from "./components/AlgorithmVisualizer";

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Sorting Algorithm Visualizer</h1>
      </header>
      <main>
        <AlgorithmVisualizer />
      </main>
      <footer>
        <p>© 2025 Algorithm Visualizer</p>
      </footer>
    </div>
  );
}

export default App;
