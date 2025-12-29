const int ledPin = 13;
const int buttonPin = 8;

int buttonState = 0;
int ledMode = 0; 
unsigned long lastBlinkTime = 0;
// For blinkingat 1 Hz
const int blinkInterval = 500;

void setup() {
  pinMode(ledPin, OUTPUT);
  pinMode(buttonPin, INPUT);
}

void loop() {
  // Read the current state
  buttonState = digitalRead(buttonPin);

  // Changing modes
  // Cycle
  if (buttonState == HIGH) {
    ledMode = (ledMode + 1) % 3;
    delay(200);
  }

  // Handle modes
  if (ledMode == 0) {
    // Off
    digitalWrite(ledPin, LOW);
  } else if (ledMode == 1) {
    // Light up
    digitalWrite(ledPin, HIGH);
  } else if (ledMode == 2) {
    // Blinking
    // When the currentTime - lastBlinkTime greater or equal blinkInterval 
    // it will blink
    unsigned long currentTime = millis();
    if (currentTime - lastBlinkTime >= blinkInterval) {
      lastBlinkTime = currentTime;
      int ledState = digitalRead(ledPin);
      digitalWrite(ledPin, !ledState);
    }
  }
}