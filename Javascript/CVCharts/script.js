// Stores dates of datapoints
const dates = [];

// Stores positive cases for each state
const MD = [0, 0, 0, 0, 0, 0];
const TX = [0, 0, 0, 0, 0];
const CA = [0, 0, 0, 0, 0];
const FL = [0, 0, 0, 0, 0];

// Stores value of new cases per day for each state
const MDinc = [0, 0, 0, 0, 0, 0];
const TXinc = [0, 0, 0, 0, 0];
const CAinc = [0, 0, 0, 0, 0];
const FLinc = [0, 0, 0, 0, 0];

// Constants for population of each state
const MDpop = 5943335;
const TXpop = 22984561;
const CApop = 39208620;
const FLpop = 21232606;
const GApop = 3989175;
const NYpop = 16861025;
const COpop = 5666813;

// Stores percent of state population testing positive
let MDpct = 0;
let TXpct = [];
let CApct = [];
let FLpct = [];

// Calls function to get JSON and populate data sets
getData();

// Calls functions to draw each chart
setTimeout(() => {
  drawChartTotals();
}, 1500);
setTimeout(() => {
  drawChartCPD();
}, 1500);
setTimeout(() => {
  drawChartPercents();
}, 1500);


//
// Function to get JSON and populate data sets
//
async function getData() {
  $.getJSON("https://covidtracking.com/api/states/daily", function (data) {
    // Variables to format dates
    let date = "";
    let month = "";
    let day = "";


//
// Function to get JSON and populate data sets
//
async function getData() {
  $.getJSON("https://covidtracking.com/api/states/daily", function (data) {
    // Variables to format dates
    let date = "";
    let month = "";
    let day = "";

    // Loop to populate data
    for (i = data.length - 1; i > 0; i--) {
      // Populate dates array
      if (date != data[i].date) {
        date = data[i].date.toString();
        month = Number(data[i].date.toString().substr(4, 2));
        day = Number(data[i].date.toString().substr(6, 2));
        dates.push(month + "/" + day);
      }

      // Populate MD data
      if (data[i].state == "MD") {
        MD.push(data[i].positive);
        MDinc.push(data[i].positiveIncrease);
        MDpct = ((data[i].positive / MDpop) * 100).toFixed(5);
        //MDpct.push(((data[i].positive / MDpop) * 100).toFixed(5));
      }

      // Populate TX data
      if (data[i].state == "TX") {
        TX.push(data[i].positive);
        TXinc.push(data[i].positiveIncrease);
        TXpct = ((data[i].positive / TXpop) * 100).toFixed(5);
        //TXpct.push(((data[i].positive / TXpop) * 100).toFixed(5));
      }

      // Populate CA data
      if (data[i].state == "CA") {
        CA.push(data[i].positive);
        CAinc.push(data[i].positiveIncrease);
        CApct = ((data[i].positive / CApop) * 100).toFixed(5);
        //CApct.push(((data[i].positive / CApop) * 100).toFixed(5));
      }

      // Populate FL data
      if (data[i].state == "FL") {
        FL.push(data[i].positive);
        FLinc.push(data[i].positiveIncrease);
        FLpct = ((data[i].positive / FLpop) * 100).toFixed(5);
        //FLpct.push(((data[i].positive / FLpop) * 100).toFixed(5));
      }

      // Populate GA data
      if (data[i].state == "GA") {
        GApct = ((data[i].positive / GApop) * 100).toFixed(5);
      }

      // Populate CO data
      if (data[i].state == "CO") {
        COpct = ((data[i].positive / COpop) * 100).toFixed(5);
      }

      // Populate NY data
      if (data[i].state == "NY") {
        NYpct = ((data[i].positive / NYpop) * 100).toFixed(5);
      }
    }
  });
}

//
// Function to draw Total Cases chart
//
async function drawChartTotals() {
  const ctx = document.getElementById("totals").getContext("2d");
  const chart = new Chart(ctx, {
    // The type of chart we want to create
    type: "line",

    // The data for our dataset
    data: {
      labels: dates,
      datasets: [
        {
          label: "MD",
          backgroundColor: "rgb(128, 191, 255, .1)",
          borderColor: "rgb(128, 191, 255, .5)",
          data: MD,
        },
        {
          label: "TX",
          backgroundColor: "rgb(255, 128, 128, .1)",
          borderColor: "rgb(255, 128, 128, .5)",
          data: TX,
        },
        {
          label: "CA",
          backgroundColor: "rgb(153, 230, 153, .1)",
          borderColor: "rgb(153, 230, 153, .5)",
          data: CA,
        },
        {
          label: "FL",
          backgroundColor: "rgb(255, 179, 102, .1)",
          borderColor: "rgb(255, 179, 102, .5)",
          data: FL,
        },
      ],
    },

    // Configuration options go here
    options: {},
  });
}

//
// Function to draw chart by percents
//
async function drawChartPercents() {
  console.log(MDpct);
  console.log(TXpct);
  new Chart(document.getElementById("percents"), {
    type: "horizontalBar",
    data: {
      labels: ["MD", "TX", "CA", "FL", "GA", "CO", "NY"],
      datasets: [
        {
          backgroundColor: [
            "rgb(128, 191, 255, .5)",
            "rgb(255, 128, 128, .5)",
            "rgb(153, 230, 153, .5)",
            "rgb(255, 179, 102, .5)",
            "rgb(255, 179, 255, .5)",
            "rgb(128, 128, 255, .5)",
            "rgb(128, 223, 255, .5)",
          ],
          borderWidth: 2,
          borderColor: [
            "rgb(128, 191, 255)",
            "rgb(255, 128, 128)",
            "rgb(153, 230, 153)",
            "rgb(255, 179, 102)",
            "rgb(255, 179, 255)",
            "rgb(128, 128, 255)",
            "rgb(128, 223, 255)",
          ],
          data: [MDpct, TXpct, CApct, FLpct, GApct, COpct, NYpct],
        },
      ],
    },

    options: {
      legend: { display: false },
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

//
// Function to draw New Cases per Day chart
//
async function drawChartCPD() {
  const ctx = document.getElementById("cpd").getContext("2d");
  const chart = new Chart(ctx, {
    // The type of chart we want to create
    type: "bar",

    // The data for our dataset
    data: {
      labels: dates,
      datasets: [
        {
          barPercentage: 0.5,
          barThickness: 6,
          maxBarThickness: 8,
          minBarLength: 2,
          label: "MD",
          backgroundColor: "rgb(128, 191, 255, 0.5)",
          borderWidth: 2,
          borderColor: "rgb(128, 191, 255, 1)",
          data: MDinc,
        },
        {
          barPercentage: 0.5,
          barThickness: 6,
          maxBarThickness: 8,
          minBarLength: 2,
          label: "TX",
          backgroundColor: "rgb(255, 128, 128, 0.5)",
          borderWidth: 2,
          borderColor: "rgb(255, 128, 128, 1)",
          data: TXinc,
        },
        {
          barPercentage: 0.5,
          barThickness: 6,
          maxBarThickness: 8,
          minBarLength: 2,
          label: "CA",
          backgroundColor: "rgb(153, 230, 153, 0.5)",
          borderWidth: 2,
          borderColor: "rgb(153, 230, 153, 1)",
          data: CAinc,
        },
        {
          barPercentage: 0.5,
          barThickness: 6,
          maxBarThickness: 8,
          minBarLength: 2,
          label: "FL",
          backgroundColor: "rgb(255, 179, 102, 0.5)",
          borderWidth: 2,
          borderColor: "rgb(255, 179, 102, 1)",
          data: FLinc,
        },
      ],
    },
    options: {},
  });
}
