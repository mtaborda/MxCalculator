var calculatorURL = "http://localhost:8080/MxCalculator/api/calculator";
var sessionsURL = "http://localhost:8080/MxCalculator/api/sessions";

var currentCalculations = [];

// Retrieve sessions list when application starts
retrieveAll();

// Calculate function call
$('#btnCalculate').click(function() {
    calculate();
    return false;
});

// Save session function call
$('#btnSave').click(function() {
    save();
    return false;
});

// Retrieve session function call
$('#btnRetrieve').click(function() {
    retrieve();
    return false;
});

function retrieveAll() {
    console.log('retrieveAll');
    $.getJSON(sessionsURL + '/all', function(data) {
            console.log('retrive all success: ' + data);
            renderSessionsList(data)
    });
}

// Calculate function
function calculate() {
    var expression = escape($('#txtExpression').val());
    console.log('calculate: ' + expression);
    $.getJSON(calculatorURL + '/calculate/' + expression, function(data) {
            console.log('calculate success: ' +data.expression+ '=' +data.result);
            renderCurrentCalculations(data)
    });
}

// Save function
function save() {
    var calcs =JSON.stringify(currentCalculations);
    console.log('save: ' + calcs);
    if (currentCalculations.length==0) {
        $('#txtResult').val("nothing to save");
    } else {
        $.getJSON(calculatorURL + '/save/' + escape(calcs), function( data ) {
                console.log('save success: ' + data);
                $('#txtResult').val("session saved");
                resetCurrentCalculations()
                renderSessionsList(data)
        });
    }
}

// Escape some chars like spaces and slashes
function escape(data) {
    return data.replace(/\//g, '%2f').replace(/\ /g, '%20');
}

// Calculate function
function retrieve() {
    var sessionId = $('#txtSessionId').val();
    console.log('retrieve: ' + sessionId);
    $.getJSON(sessionsURL + '/' + sessionId, function(data) {
            console.log('retrieve success: ' + data.expression);
            renderSessionEntriesList(data)
    });
}

// Render current session calculations
function renderCurrentCalculations(data) {
    $('#txtResult').val(data.result);
    $('#ulCalculationsList').find('li').remove();
    $.each(currentCalculations, function(index, calculation) {
        $('#ulCalculationsList').append('<li>' +calculation.expression+ ' = ' +calculation.result+ '</li>');
    });
    currentCalculations.push(data);
}

// Reset the current calculations array
function resetCurrentCalculations() {
    currentCalculations = []
    $('#ulCalculationsList').find('li').remove();
}

// Render sessions
function renderSessionsList(data) {
    $('#ulSessionsList').find('li').remove();
    $.each(data, function(index, session) {
        $('#ulSessionsList').append('<li> Session => ' +session.id+ '</li>');
    });
}

// Render session entries
function renderSessionEntriesList(data) {
    $('#ulEntriesList').find('li').remove();
    $.each(data, function(index, entry) {
        $('#ulEntriesList').append('<li>' +entry.expression+ ' = ' +entry.result+ '</li>');
    });
}
