#ifndef MY_LED_H
#define MY_LED_H

#include <Arduino.h>

const uint8_t BUILT_IN_led = 5;		// pin 13 on metro is PORTB pin 5 on Atmega
const uint8_t EXTERNAL_led = 4; 	// pin 12 on metro is PORTB pin 4 on Atmega

void initialize_led(uint8_t led_t);
void led_on(uint8_t led_t);
void led_off(uint8_t led_t);
void led_toggle(uint8_t led_t);
void led_flash(uint8_t led_t);

//initialize_led
void initialize_led(uint8_t led_t) {
  DDRB |= (1 << led_t);
  led_flash(led_t);
}

//led_on
void led_on(uint8_t led_t) {
	// led_t: led type is either BUILT_IN or EXTERNAL 
	PORTB |= (1 << led_t);
}

//led_off
void led_off(uint8_t led_t) {
  PORTB &= ~(1 << led_t);
}

//led_toggle
void led_toggle(uint8_t led_t) {
  PORTB ^= (1 << led_t);
}

//led_flash
void led_flash(uint8_t led_t) {
  for (int i = 0; i < 3; i++) {
    led_on(led_t);
    delay(200);
    led_off(led_t);
    delay(200);
  }
}

#endif