import React, { useState } from "react";
import "../styles/AlgorithmControls.css";

const AlgorithmControls = ({ startSorting, connected, disabled }) => {
  const [algorithmType, setAlgorithmType] = useState("BUBBLE");
  const [arraySize, setArraySize] = useState(20);
  const [delay, setDelay] = useState(200);

  const handleStartClick = () => {
    startSorting(algorithmType, arraySize, delay);
  };

  return (
    <div className="algorithm-controls">
      <div className="control-group">
        <label htmlFor="algorithm-select">Algorithm:</label>
        <select
          id="algorithm-select"
          value={algorithmType}
          onChange={(e) => setAlgorithmType(e.target.value)}
          disabled={disabled}
        >
          <option value="BUBBLE">Bubble Sort</option>
          <option value="SELECTION">Selection Sort</option>
          <option value="INSERTION">Insertion Sort</option>
          <option value="QUICK">Quick Sort</option>
          <option value="MERGE">Merge Sort</option>
        </select>
      </div>

      <div className="control-group">
        <label htmlFor="array-size">Array Size:</label>
        <input
          id="array-size"
          type="range"
          min="5"
          max="100"
          value={arraySize}
          onChange={(e) => setArraySize(parseInt(e.target.value))}
          disabled={disabled}
        />
        <span className="value-display">{arraySize}</span>
      </div>

      <div className="control-group">
        <label htmlFor="animation-speed">Animation Speed:</label>
        <input
          id="animation-speed"
          type="range"
          min="50"
          max="1000"
          step="50"
          value={delay}
          onChange={(e) => setDelay(parseInt(e.target.value))}
          disabled={disabled}
        />
        <span className="value-display">{delay}ms</span>
      </div>

      <button
        className="start-button"
        onClick={handleStartClick}
        disabled={!connected || disabled}
      >
        {disabled ? "Sorting..." : "Start Sorting"}
      </button>

      <div className="connection-status">
        Status:{" "}
        <span className={connected ? "connected" : "disconnected"}>
          {connected ? "Connected" : "Disconnected"}
        </span>
      </div>
    </div>
  );
};

export default AlgorithmControls;
