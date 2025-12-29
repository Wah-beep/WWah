# Wah Saw Tamalar
# largestInArray.asm
# Find the largest number in an array

.data
array: 		.word 	3, -8, 2, 7, 20, 0, -2, 15, 21, 10	# An array
asize: 		.word 	10			# Length
largest: 	.word 	0			# Default value = 0

.text
main:
	# Push $ra onto the stack
	addi $sp, $sp, -4
	sw $ra, 0($sp)
	
	# Load arguments and call function
	la $a0, array		# Base address
	lw $a1, asize		# Length of array
	jal findLargest		# Jump and link to function findLargest
	sw $v0, largest		# Store the return from function to largest
	
	# Print
	lw $a0, largest
	addi $v0, $zero, 1	# 1 print an integer
	syscall
	
	# Restore $ra 
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	jr $ra
	
# findLargest function
findLargest:
	# Push registers onto the stack
	addi $sp, $sp, -20
	sw $ra, 0($sp)		# return address
	sw $s0, 4($sp)		# Base address
	sw $s1, 8($sp)		# Length of array
	sw $s2, 12($sp)		# To store largest number
	sw $s3, 16($sp)		# Index counter
	
	# Copy arguments to registers
	addi $s0, $a0, 0	# Copy base address
	addi $s1, $a1, 0	# Copy length
	
	# Initialize index counter and largest
	lw $s2, 0($s0)		# Load first number to $s2
	add $s3, $zero, 1	# Counter at 1
	
	# Loop
	top:
		beq $s1, $s3, exit	# Exit if index counter = length
		sll $t0, $s3, 2		# Index counter * 4 -> offset
		add $t1, $s0, $t0	# $t1 has base + offset
		lw $t2, 0($t1)		# Value from array into $t2
		
		slt $t3, $s2, $t2	# If $s2 less than $t2, $t3 = 1
		beq $t3, 1, changeLargest
		
		addi $s3, $s3, 1	# Increment the index counter by 1
		j top
	
	changeLargest:
		move $s2, $t2		# Replace $s2 with $t2, larger number
		addi $s3, $s3, 1	# Increment the index counter by 1
		j top
		
	exit: 
		addi $v0, $s2, 0	# Add largest to return register $v0
		
		# Restore the stack
		lw $ra, 0($sp)
		lw $s0, 4($sp)
		lw $s1, 8($sp)
		lw $s2, 12($sp)
		lw $s3, 16($sp)
		addi $sp, $sp, 20
		
		jr $ra				# Return to caller



 