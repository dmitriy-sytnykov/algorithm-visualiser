import React, { useRef } from "react";
import "../../styles/BarChart.css";

const BarChart = ({ data = [], currentIndex, compareIndex, complete }) => {
  const chartRef = useRef(null);

  const maxValue = data.length > 0 ? Math.max(...data) : 0;

  const barWidth = data.length > 0 ? 100 / data.length : 0;

  const getBarColor = (index) => {
    if (complete) return "#4CAF50"; // Green when sorting is complete
    if (index === currentIndex) return "#FF9800"; // Orange for current index
    if (index === compareIndex) return "#E91E63"; // Pink for compare index
    return "#3498db"; // Default blue
  };

  const getBarHeight = (value) => {
    return maxValue > 0 ? (value / maxValue) * 100 : 0;
  };

  return (
    <div className="bar-chart" ref={chartRef}>
      {data.map((value, index) => (
        <div
          key={index}
          className="bar"
          style={{
            height: `${getBarHeight(value)}%`,
            width: `${barWidth}%`,
            backgroundColor: getBarColor(index),
          }}
          data-value={value}
        >
          {data.length <= 30 && <span className="bar-value">{value}</span>}
        </div>
      ))}
    </div>
  );
};

export default BarChart;
