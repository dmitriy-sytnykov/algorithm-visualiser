import React from "react";
import "../styles/AlgorithmInfo.css";

const AlgorithmInfo = ({
  compareCount,
  swapCount,
  currentStep,
  inProgress,
}) => {
  return (
    <div className="algorithm-info">
      <div className="info-box">
        <div className="info-header">Statistics</div>
        <div className="info-content">
          <div className="stat-item">
            <span className="stat-label">Comparisons:</span>
            <span className="stat-value">{compareCount}</span>
          </div>
          <div className="stat-item">
            <span className="stat-label">Swaps:</span>
            <span className="stat-value">{swapCount}</span>
          </div>
        </div>
      </div>

      <div className="info-box current-step">
        <div className="info-header">Current Step</div>
        <div className="info-content">
          <div className="step-description">
            {inProgress
              ? currentStep || "Processing..."
              : compareCount > 0
              ? "Sorting complete!"
              : "Ready to start"}
          </div>
          {inProgress && (
            <div className="progress-indicator">
              <div className="spinner"></div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default AlgorithmInfo;
