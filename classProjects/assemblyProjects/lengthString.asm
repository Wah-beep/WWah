# Wah Saw Tamalar
# lengthString.asm
# Finding length of a string

.data
string1: 		.asciiz 	"augsburg"			# String
strLen:			.word 		0					# Length of a string

.text
main:
	# Push $ra onto the stack
	addi $sp, $sp, -4
	sw $ra, 0($sp)
	
	# Load a argument and call function
	la $a0, string1
	jal funLength
	sw $v0, strLen
	
	# Print
	lw $a0, strLen
	addi $v0, $zero, 1	# 1 print an integer
	syscall
	
	# Restore $ra
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	jr $ra

funLength:
	addi $t1, $zero, 0		# Length
	
funLengthLoop:
	lb $t0, 0($a0)				# Load byte
	beq $t0, $zero, exit		# If byte equal null, exit
	
	addi $t1, $t1, 1			# Increment length
	addi $a0, $a0, 1			# Move byte
	
	j funLengthLoop
	
exit:
	addi $v0, $t1, 0
	jr $ra