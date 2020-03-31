// Set form and output DOM elements
var form = document.getElementById('tipForm');
var output = document.getElementById('output');

// Declare variables for input fields
var total;
var people;
var service;
var tip;

// Form Submit Event
form.addEventListener('submit', calculateTip);

// Form Clear Event
form.addEventListener('reset', clearForm);

// Calculate tip function
function calculateTip(e) {
    e.preventDefault();

    // Set variables to form input
    total = Math.abs(parseFloat(form.total.value.replace('$', ''))).toFixed(2);
    people = Math.abs(parseFloat(form.people.value));
    service = form.service.value;

    clearOutput();

    checkInput();

    // Calculate tip and output results
    if (total && total != 'NaN' && people && people % 1 == 0 && service) {
        // Calculate tip and format to two decimals
        tip = parseFloat(Math.ceil((total * service / people) * 100) / 100).toFixed(2);

        form.total.value = total;
        form.people.value = people;

        printOutput();
    }
}

// Clear Form function
function clearForm(e) {
    e.preventDefault();

    clearOutput();

    // Reset total field
    form.total.value = '';
    form.total.className = "form-control";

    // Reset people field
    form.people.value = '';
    form.people.className = "form-control";

    // Reset service field
    form.service.value = '';
    form.service.className = "form-control";
}

function checkInput() {

    // Verify total input is valid
    if (total && total != 'NaN') {
        form.total.className = "form-control"
    } else form.total.className = "form-control is-invalid";

    // Verify people input is valid
    if (people && people % 1 == 0) {
        form.people.className = "form-control"
    } else form.people.className = "form-control is-invalid";

    // Verify service selection is made, convert to decimal
    if (service == 'great') {
        service = .20;
        form.service.className = "form-control"
    } else if (service == 'average') {
        service = .15;
        form.service.className = "form-control"
    } else if (service == 'poor') {
        service = .10;
        form.service.className = "form-control"
    } else {
        service = '';
        form.service.className = "form-control is-invalid"
    }
}

function printOutput() {

    clearOutput();

    // Create new h3 output
    var h3 = document.createElement('h3');
    // Add Class
    h3.className = "text-center";
    // Add text node with input value
    if (people == 1) {
        h3.appendChild(document.createTextNode(`Tip total:`));
    } else {
        h3.appendChild(document.createTextNode(`Tip per person:`));
    }

    // Create new h1 output
    var h1 = document.createElement('h1');
    // Add Class
    h1.className = "text-center text-success";
    // Add text node with input value
    h1.appendChild(document.createTextNode(`$${tip}`));

    // Append output to output div
    output.appendChild(h3);
    output.appendChild(h1);
}

function clearOutput() {
    // Reset output div
    output.textContent = '';
}