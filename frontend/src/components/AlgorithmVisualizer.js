import React, { useState, useEffect, useRef } from "react";
import AlgorithmControls from "./AlgorithmControls";
import BarChart from "./graphs/BarChart";
import AlgorithmInfo from "./AlgorithmInfo";
import "../styles/AlgorithmVisualizer.css";

// Backend server URL
const WS_URL = "ws://localhost:8080/algorithm-ws";

const AlgorithmVisualizer = () => {
  const [socket, setSocket] = useState(null);
  const [connected, setConnected] = useState(false);
  const [arrayData, setArrayData] = useState([]);
  const [currentStep, setCurrentStep] = useState(null);
  const [sortingInProgress, setSortingInProgress] = useState(false);
  const [algorithmInfo, setAlgorithmInfo] = useState({
    swapCount: 0,
    compareCount: 0,
    currentStep: "",
  });

  const socketRef = useRef(null);

  useEffect(() => {
    const connectWebSocket = () => {
      const newSocket = new WebSocket(WS_URL);
      socketRef.current = newSocket;

      newSocket.onopen = () => {
        console.log("WebSocket connected");
        setConnected(true);
        setSocket(newSocket);
      };

      newSocket.onmessage = (event) => {
        const data = JSON.parse(event.data);

        setArrayData(data.array);

        setAlgorithmInfo({
          swapCount: data.swapCount || 0,
          compareCount: data.compareCount || 0,
          currentStep: data.step || "",
        });

        setCurrentStep({
          currentIndex: data.currentIndex,
          compareIndex: data.compareIndex,
          complete: data.complete,
        });

        setSortingInProgress(!data.complete);
      };

      newSocket.onclose = () => {
        console.log("WebSocket disconnected");
        setConnected(false);

        setTimeout(() => {
          if (socketRef.current === newSocket) {
            connectWebSocket();
          }
        }, 5000);
      };

      newSocket.onerror = (error) => {
        console.error("WebSocket error:", error);
      };

      return newSocket;
    };

    const ws = connectWebSocket();

    return () => {
      if (ws) {
        ws.close();
      }
      setConnected(false);
    };
  }, []);

  const startSorting = (algorithmType, arraySize, delay) => {
    if (!connected || sortingInProgress) return;

    const newArray = generateRandomArray(arraySize);
    setArrayData(newArray);

    setAlgorithmInfo({
      swapCount: 0,
      compareCount: 0,
      currentStep: "Starting...",
    });

    if (socket && socket.readyState === WebSocket.OPEN) {
      socket.send(
        JSON.stringify({
          algorithmType: algorithmType,
          array: newArray,
          delay: delay,
        })
      );

      setSortingInProgress(true);
    }
  };

  const generateRandomArray = (size) => {
    const array = [];
    for (let i = 0; i < size; i++) {
      array.push(Math.floor(Math.random() * 100) + 1);
    }
    return array;
  };

  return (
    <div className="algorithm-visualizer">
      <AlgorithmControls
        startSorting={startSorting}
        connected={connected}
        disabled={sortingInProgress}
      />
      <div className="visualization-container">
        <BarChart
          data={arrayData}
          currentIndex={currentStep ? currentStep.currentIndex : -1}
          compareIndex={currentStep ? currentStep.compareIndex : -1}
          complete={currentStep ? currentStep.complete : false}
        />
      </div>
      <AlgorithmInfo
        compareCount={algorithmInfo.compareCount}
        swapCount={algorithmInfo.swapCount}
        currentStep={algorithmInfo.currentStep}
        inProgress={sortingInProgress}
      />

      {!connected && (
        <div className="connection-warning">
          <p>
            Not connected to server. Please check if the backend is running.
          </p>
        </div>
      )}
    </div>
  );
};

export default AlgorithmVisualizer;
