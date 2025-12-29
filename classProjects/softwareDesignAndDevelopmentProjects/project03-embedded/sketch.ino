#include <Arduino.h>
#include <TimerOne.h>
#include "my_led.h"

const unsigned long ms_delay = 500;
uint8_t count = 0;
uint8_t INT_PIN = 0;	// on PORTB (see Adafruit Metro Pinout).

const int yellowLedPin = 12;
const int blueLedPin = 11;

volatile uint8_t pinState = 0;
volatile unsigned int activeCount = 0;

void toggle_external_led(){
  led_toggle(EXTERNAL_led);
}

void setup() {
  pinMode(blueLedPin, OUTPUT);
  pinMode(yellowLedPin, OUTPUT);
  pinMode(13, OUTPUT);

  initialize_led(EXTERNAL_led);
  initialize_led(BUILT_IN_led);
  

  unsigned long us_delay = ms_delay * 1000;
  Timer1.initialize(us_delay);
  Timer1.attachInterrupt(toggle_external_led);

  // Pin Change Interrupt Control Register. Enable interrupts on any pin 0-7
	PCICR |= 0x01;
		
	// Pin Change Mask for pins 0-7: Enable specific interrupt PCINT0 (pin 0)
	PCMSK0 |= (1 << INT_PIN);

	// Set interrupt pin as input (meaning the signal will be read)
	DDRB &= ~(1 << INT_PIN);

	// 
	pinState = 0;
}

void loop() {
  count++;
	if (count >= 10) {
		led_toggle(BUILT_IN_led);
		count = 0;
	}
	delay(50);

  // this is a way to simulate a peripheral device activating the interrupt
  // increase the active count at each loop by some random amount
  // once we get to the threshold, signal a pin change
  activeCount += random(10);
  
  // is it time for a pin change?
  if (activeCount > 500) {
  	// change the state on that pin to trigger the PCINT
    if (pinState==0) {
      PORTB |= (1 << INT_PIN);
      pinState = 1;
    } else {
      PORTB &= ~(1 << INT_PIN);
      pinState = 0;
    }
    activeCount = 0; 
  }
  
  // code to control the toggle of the external led on pin 12 using delay(50)

} 
/*
  This is the callback function for a PCINT0 interrupt as required by
  the Atmega processor. If there is a pin change on PORTB pin 0,
  this function will be called.
  */
ISR(PCINT0_vect) {
  // delay() will not work inside of an interrupt.
  // a trick is to run a for-loop many,many times instead.

  // the counter must be long so it can count very high
  unsigned long i=0;

  // volatile is a key word telling the compiler not to ignore this
  // without it the for loop will not run
  volatile int k=0;
  
  // number of flashes
  int j=0;
  for (j=0; j<4; j++) {
    digitalWrite(blueLedPin,HIGH);
    for (i=0; i<500000; i++) {
      k++;	// filler to not have an empty for loop
    }
    digitalWrite(blueLedPin,LOW);
    for (i=0; i<500000; i++) {
      k--;
    }
  }
}
