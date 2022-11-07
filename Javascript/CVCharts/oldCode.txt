/*

// Function to draw Pie Chart
async function drawPie() {
  const ctx = document.getElementById("pie1").getContext("2d");
  // For a pie chart
  const myPieChart = new Chart(ctx, {
    type: "doughnut",
    data: {
      labels: ["Cases", "Population"],
      datasets: [
        {
          data: [MDcases, MDpop],
          backgroundColor: "rgba(200, 0, 0, 0.1)",
        },
      ],
    },
  });
}

*/

/*

// Functionto draw Daily New Cases Line Graph
async function drawChart_PositiveIncrease() {

  const ctx = document.getElementById("_positiveIncrease").getContext("2d");
  const chart = new Chart(ctx, {
    // The type of chart we want to create
    type: "line",

    // The data for our dataset
    data: {
      labels: dates,
      datasets: [
        {
          label: "MD",
          backgroundColor: "rgb(128, 191, 255, 0.1)",
          borderColor: "rgb(128, 191, 255, .5)",
          data: MDinc,
        },
        {
          label: "TX",
          backgroundColor: "rgb(255, 128, 128, .1)",
          borderColor: "rgb(255, 128, 128, .5)",
          data: TXinc,
        },
        {
          label: "CA",
          backgroundColor: "rgb(153, 230, 153, .1)",
          borderColor: "rgb(153, 230, 153, .5)",
          data: CAinc,
        },
        {
          label: "FL",
          backgroundColor: "rgb(255, 179, 102, .1)",
          borderColor: "rgb(255, 179, 102, .5)",
          data: FLinc,
        },
      ],
    },

    // Configuration options go here
    options: {},
  });
}

*/

/*

// Function to draw Positive Population Percentage chart
async function drawChartPercents() {
  const ctx = document.getElementById("percents").getContext("2d");
  const chart = new Chart(ctx, {
    // The type of chart we want to create
    type: "line",

    // The data for our dataset
    data: {
      labels: dates,
      datasets: [
        {
          label: "MD",
          backgroundColor: "rgb(128, 191, 255, 0.01)",
          borderColor: "rgb(128, 191, 255, .5)",
          data: MDpct,
          steppedLine: "middle",
        },
        {
          label: "TX",
          backgroundColor: "rgb(255, 128, 128, .01)",
          borderColor: "rgb(255, 128, 128, .5)",
          data: TXpct,
          steppedLine: "middle",
        },
        {
          label: "CA",
          backgroundColor: "rgb(153, 230, 153, .01)",
          borderColor: "rgb(153, 230, 153, .5)",
          data: CApct,
          steppedLine: "middle",
        },
        {
          label: "FL",
          backgroundColor: "rgb(255, 179, 102, .01)",
          borderColor: "rgb(255, 179, 102, .5)",
          data: FLpct,
          steppedLine: "middle",
        },
      ],
    },

    // Configuration options go here
    options: {
      scales: {
        yAxes: [
          {
            scaleLabel: {
              display: true,
              labelString: "% of State Population",
            },
          },
        ],
      },
    },
  });
}

*/
/*
async function drawChartPercents() {
  new Chart(document.getElementById("percents"), {
    type: "horizontalBar",
    data: {
      labels: ["MD", "FL", "TX", "2", "3", "4"],
      datasets: [
        {
          barPercentage: 0.5,
          barThickness: 6,
          maxBarThickness: 8,
          minBarLength: 2,
          label: "MD",
          backgroundColor: "rgb(128, 191, 255, 1)",
          borderWidth: 2,
          borderColor: "rgb(128, 191, 255, 1)",
          data: MDpct,
        },
        {
          label: "TX",
          backgroundColor: "rgb(255, 128, 128)",
          borderColor: "rgb(255, 128, 128, .5)",
          data: TXpct,
        },
        {
          label: "CA",
          backgroundColor: "rgb(153, 230, 153, .1)",
          borderColor: "rgb(153, 230, 153, .5)",
          data: CApct,
        },
        {
          label: "FL",
          backgroundColor: "rgb(255, 179, 102, .1)",
          borderColor: "rgb(255, 179, 102, .5)",
          data: FLpct,
        },
      ],
    },

    options: {
      scales: {
        xAxes: [
          {
            scaleLabel: {
              display: true,
              labelString: "% of State Population",
            },
          },
        ],
      },
    },
  });
}
*/
