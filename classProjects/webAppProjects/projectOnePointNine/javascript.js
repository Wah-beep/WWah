/**
 * Wah Saw Tamalar
 * javascript.js
 * Add food list on the left side
 * First, only the food list is display
 * Then, when click on the list. The image is display along with details
 */

// Food Items
let foodItems = [
    {name: "Pho", servingSize: "L", numCalories: 350, foodOrigin: "Asia", foodImage: "images/foodPho.jpg"},
    {name: "Pizza", servingSize: "M", numCalories: 2500, foodOrigin: "Italy", foodImage: "images/foodPizza.webp"},
    {name: "Ice Cream", servingSize: "S", numCalories: 180, foodOrigin: "Unknown", foodImage: "images/foodIceCream.jpg"}
];

/** 
 * First attempt
 * Adding the foodItems to ul.
let ul = document.querySelector("ul");
for (let item of foodItems) {
    ul.innerHTML += `<li>${item.name}</li>`;
}

// List MouseOver Event
let li = document.getElementById("foodItems");
li.addEventListener("mouseover", function(e) {
    e.target.style.color = "orange";
});

// List MouseOut Event
li.addEventListener("mouseout", function(e) {
    e.target.style.color = "white";
});

 * // List Click And Displaying Images and turn red when click on
 * let liElement = document.querySelectorAll("li");
let picElement = document.querySelector(".foodDetails");
for (let i = 0; i < liElement.length; i++) {
    liElement[i].addEventListener("click", function() {
        this.style.color = "red";
        let item = foodItems[i];
        picElement.innerHTML = `
            <img src="${item.foodImage}" alt="${item.name}">
            <p>Size: ${item.servingSize}, Calories: ${item.numCalories}, Origin: ${item.foodOrigin}</p>
            `;
    });
}
*/

window.addEventListener( "DOMContentLoaded", printList)

/**
 * Create a list of food
 */
function printList() {

    // Create li for each foodItems
    // Loop through foodItems add <li>foodName</li> to letPrint
    let letPrint = "";
    for (let item of foodItems) {
        let foodName = item.name;
        letPrint += "<li>" + foodName + "</li>\n";
    }

    // Print the li with innerHTML
    let wherePrint = document.getElementById("foodItems");
    wherePrint.innerHTML = letPrint;

    // Add click event to li that are children of foodItems
    // Add mouseover and mouseout event
    let selectLi = document.querySelectorAll("#foodItems > li");
    for (aLi of selectLi) {
        aLi.addEventListener("click", clickOnList);
        aLi.addEventListener("mouseover", ListMouseOver);
        aLi.addEventListener("mouseout", ListMouseOut);
    }
}

function clickOnList(event) {

    // Loop through foodItems and compare it with nameFood
    // And print them with innerHTML
    nameFood = this.textContent;
    for (item of foodItems) {
        if (item.name == nameFood) {

            let spanServingSize = document.getElementById("servingSize");
            let spanCalories = document.getElementById("numCalories");
            let spanOrigin = document.getElementById("foodOrigin");
            spanServingSize.textContent = item.servingSize;
            spanCalories.textContent = item.numCalories;
            spanOrigin.textContent = item.foodOrigin;

            // Get img element from the foodDetails
            // Add src and alt
            let imgEle = document.querySelector(".foodDetails > img");
            imgEle.src = item.foodImage;
            imgEle.alt = item.name;
        }
    }
}

function ListMouseOver(e) {
    e.target.style.color = "orange";
}

function ListMouseOut(e) {
    e.target.style.color = "antiquewhite";
}











