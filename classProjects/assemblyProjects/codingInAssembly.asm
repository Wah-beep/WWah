#+++++
# Wah Saw Tamalar
# codingInAssembly.asm
# MIPS assembly language program that multiplies two numbers together.
# Using addition inside of a loop.
# X times Y can be calculated by adding the number X, Y times.
#-----

#+++++
# Data Segment
#-----

.data

x: .word 7
y: .word 3

#+++++
# Program Segment
#-----

.text

main:
	lw $s0, x
	lw $s1, y
	topOfLoop: # start of the loop
		beq $s1, $zero, endOfLoop # when y reaches zero
		add $s2, $s2, $s0 # add x to $s2
		addi $s1, $s1, -1 # count down y
		j topOfLoop # jump to the top of the loop
	endOfLoop: # end of the loop
	nop
		
	
