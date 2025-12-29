/**
 * Wah Saw Tamalar
 * javascript.js
 */

// Array of contacts
let userContact = [];

// Main
//window.addEventListener( "DOMContentLoaded", main)

// jQuery version
$(document).ready(main);

/**
 * Call loadContacts, and printContact
 * Three buttons: submit, edit, save
 */
function main() {

    // Call loadContacts to load data store in localStorage
    loadContacts();

    // First print list
    printContact();

    /*
    // When submit button is click, calls addContact
    let submitButton = document.getElementById("submitBtn");
    submitButton.addEventListener("click", addContact);

    // When edit button is click, calls fillContactInfo
    let editButton = document.getElementById("editBtn");
    editButton.addEventListener("click", fillContactInfo);

    //When save button is click, calls editContact
    let saveButton = document.getElementById("saveBtn");
    saveButton.addEventListener("click", editContact);
    */

    // jQuery version
    let $submitButton = $("#submitBtn");
    $submitButton.on("click", addContact);

    let $editButton = $("#editBtn");
    $editButton.on("click", fillContactInfo);

    let $saveButton = $("#saveBtn");
    $saveButton.on("click", editContact);

    let $deleteButton = $("#deleteBtn");
    $deleteButton.on("click", deleteCon);
}

/**
 * Going through the array and adding names to the empty string with li tag
 * When user click on the list, it displays the info
 */
function printContact() {

    // Empty string
    let letPrint = "";

    // Get names from array and adding to the empty string
    for (let each of userContact) {
        var name = each.name;
        letPrint += "<li>" + name + "</li>";
    }

    /*
    // Convert the empty string to HTML
    let wherePrint = document.getElementById("userList");
    wherePrint.innerHTML = letPrint;

    // When the list is click, calls displayContactInfo
    let selectLi = document.querySelectorAll("#userList > li");
    for (let aLi of selectLi) {
        aLi.addEventListener("click", displayContactInfo);
    }
    */

    // jQuery Version
    let $wherePrint = $("#userList");
    $wherePrint.html(letPrint);

    let $selectLi = $("#userList > li");
    $selectLi.on("click", displayContactInfo);
}

/**
 * Get the spans from HTML and add infos of it to display it
 */
function displayContactInfo() {

    var nameCon = this.textContent;

    // Loop through userContact to the name
    for (let each of userContact) {
        if (each.name === nameCon) {

            /*
            // Getting spans
            var spanConName = document.getElementById("contactName"); 
            var spanConStName = document.getElementById("contactStreetName"); 
            var spanConHouseNum = document.getElementById("contactHouseNumber"); 
            var spanConCountry = document.getElementById("contactCountry"); 
            var spanConPostalCode = document.getElementById("contactPostalCode"); 
            var spanConPhoneNum = document.getElementById("contactPhoneNumber");
            var spanConCity = document.getElementById("contactCity");
            var spanConState = document.getElementById("contactState");

            // Replace the current span value with data from the array to display it
            spanConName.textContent = each.name;
            spanConStName.textContent = each.streetName;
            spanConHouseNum.textContent = each.houseNum;
            spanConCountry.textContent = each.countryName;
            spanConPostalCode.textContent = each.postalCodeNum;
            spanConPhoneNum.textContent = each.phoneNum;
            spanConCity.textContent = each.cityName;
            spanConState.textContent = each.stateName;

            */
           
            // jQuery Version
            var $spanConName = $("#contactName");
            var $spanConStName = $("#contactStreetName");
            var $spanConHouseNum = $("#contactHouseNumber");
            var $spanConCountry = $("#contactCountry");
            var $spanConPostalCode = $("#contactPostalCode");
            var $spanConPhoneNum = $("#contactPhoneNumber");
            var $spanConCity = $("#contactCity");
            var $spanConState = $("#contactState");

            $spanConName.text(each.name);
            $spanConStName.text(each.streetName);
            $spanConHouseNum.text(each.houseNum);
            $spanConCountry.text(each.countryName);
            $spanConPostalCode.text(each.postalCodeNum);
            $spanConPhoneNum.text(each.phoneNum);
            $spanConCity.text(each.cityName);
            $spanConState.text(each.stateName);

            // For editing
            localStorage.setItem("editingContact", each.name);
        }
    }
}

/**
 * Get the city and state, and add to the array
 */
function addContact() {

    /*
    // Get API
    const zipCode = document.getElementById("userPostalCode").value.trim();
    var xhr = new XMLHttpRequest();
    xhr.addEventListener("load", getStuff);
    xhr.open("GET", "https://api.zippopotam.us/us/" + zipCode, true);
    xhr.send();
    */

    // jQuery Version
    const zipCode = $("#userPostalCode").val().trim();
    $.get("https://api.zippopotam.us/us/" + zipCode, function(data){
        getStuff(data);
    })
    .fail(function() {
        console.log("Error Request");
    });
}

/**
 * Put data to the array from localStorage
 */
function loadContacts() {
    
    // Loop through localStorage
    for (let i = 0; i < localStorage.length; i++) {
        // Get key
        const key = localStorage.key(i);
        console.log("localStorage Key: " + key);

        // Skip
        if (key === "editingContact") continue;

        // Get the value
        const value = localStorage.getItem(key);

        const contact = JSON.parse(value);
        // Add the data to the array
        userContact.push(contact);
        console.log("Value[key] " + contact);
    }
    console.log("Array: " + userContact);
}

/**
 * When the edit is click, the textbox is filled to edit
 */
function fillContactInfo() {

    // Get the edit
    var selectName = localStorage.getItem("editingContact");

    // Find selected name
    var contact = userContact.find(c => c.name == selectName);
    if (contact) {

        /*
        // Fill the input for editing
        document.getElementById("userName").value = contact.name;
        document.getElementById("userStreetName").value = contact.streetName;
        document.getElementById("userHouseNumber").value = contact.houseNum;
        document.getElementById("userCountry").value = contact.countryName;
        document.getElementById("userPostalCode").value = contact.postalCodeNum;
        document.getElementById("userPhoneNumber").value = contact.phoneNum;
        */

        // jQuery Version
        $("#userName").val(contact.name);
        $("#userStreetName").val(contact.streetName);
        $("#userHouseNumber").val(contact.houseNum);
        $("#userCountry").val(contact.countryName);
        $("#userPostalCode").val(contact.postalCodeNum);
        $("#userPhoneNumber").val(contact.phoneNum);
    }
}

/**
 * Update the array after edits
 */
function editContact () {

    // Get value from 
    var oldName = localStorage.getItem("editingContact");

    // Get existing contact
    var oldContact = userContact.find(c => c.name === oldName);
    if (!oldContact) return;

    // Update the array
    var updatedContact = {
        /*
        name: document.getElementById("userName").value,
        streetName: document.getElementById("userStreetName").value,
        houseNum: document.getElementById("userHouseNumber").value,
        countryName: document.getElementById("userCountry").value,
        postalCodeNum: document.getElementById("userPostalCode").value,
        phoneNum: document.getElementById("userPhoneNumber").value,
        cityName: oldContact.cityName,
        stateName: oldContact.stateName
        */

        // jQuery Version
        name: $("#userName").val(),
        streetName: $("#userStreetName").val(),
        houseNum: $("#userHouseNumber").val(),
        countryName: $("#userCountry").val(),
        postalCodeNum: $("#userPostalCode").val(),
        phoneNum: $("#userPhoneNumber").val(),
        cityName: oldContact.cityName,
        stateName: oldContact.stateName
    };

    // Replace old contact with updated one
    for (let i = 0; i < userContact.length; i++) {
        if (userContact[i].name == oldName) {
            userContact[i] = updatedContact;
            break;
        }
    }

    // Update localStorage
    localStorage.removeItem(oldName);
    localStorage.setItem(updatedContact.name, JSON.stringify(updatedContact));
    localStorage.removeItem("editingContact");

    // Reprint the list
    printContact();
    displayContactInfo();
}

// Get City and State
function getStuff(data) {

    /*
    if (this.status !== 200) {
        alert("Error making HTTP request");
        return;
    }
    */

    //const data = JSON.parse(this.response);
    const place = data.places[0];
    const city = place["place name"];
    const state = place["state"];

    /*
    // Get data from textbox
    var inputName = document.getElementById("userName").value;
    var inputStreetName = document.getElementById("userStreetName").value;
    var inputHouseNum = document.getElementById("userHouseNumber").value;
    var inputCountry = document.getElementById("userCountry").value;
    var inputPostalCodeNum = document.getElementById("userPostalCode").value;
    var inputPhoneNum = document.getElementById("userPhoneNumber").value;
    */

    // jQuery Version
    var inputName = $("#userName").val();
    var inputStreetName = $("#userStreetName").val();
    var inputHouseNum = $("#userHouseNumber").val();
    var inputCountry = $("#userCountry").val();
    var inputPostalCodeNum = $("#userPostalCode").val();
    var inputPhoneNum = $("#userPhoneNumber").val();


    // Add data to the array
    var aContact = {
        name: inputName,
        streetName: inputStreetName,
        houseNum: inputHouseNum,
        countryName: inputCountry,
        postalCodeNum: inputPostalCodeNum,
        phoneNum: inputPhoneNum,
        cityName: city,
        stateName: state
    };

    // Add the data to the array
    userContact.push(aContact);

    // Store to localStorage
    localStorage.setItem(inputName, JSON.stringify(aContact));

    // Print the list
    printContact();
}

/**
 * Delete Contact
 */
function deleteCon() {

    var nameCon = this.textContent.trim();

    localStorage.removeItem(nameCon);

    loadContacts();

    displayContactInfo();
}
