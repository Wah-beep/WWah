# Wah Saw Tamalar
.data
	x: .word 2
	y: .word 3
	str1: .asciiz " times "		# String one
	str2: .asciiz " is "		# String two

.text
main:
	# stack
	addi $sp, $sp, -4
	sw $ra, 0($sp)
	
	lw $a0, x			# Load value from memory in to $a0
	lw $a1, y			# Load value from memory in to $a1
	
	jal multiplier
	
	move $s1, $v0
	
	lw $a0, x
	li $v0, 1
	syscall
	
	la $a0, str1
	li $v0, 4
	syscall
	
	lw $a0, y
	li $v0, 1
	syscall
	
	la $a0, str2
	li $v0, 4
	syscall
	
	move $a0, $s1
	li $v0, 1
	syscall
	
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	jr $ra
	
multiplier:
	# stack
	addi $sp, $sp, -8
	sw $s0, 4($sp)
	sw $ra, 0($sp)
	li $s0, 0
	# Multiplier function
	topOfLoop:
		beq $a1, $zero, endOfLoop
		add $s0, $s0, $a0
		addi $a1, $a1, -1
		j topOfLoop
	endOfLoop:
		move $v0, $s0
		lw $s0, 4($sp)
		lw $ra, 0($sp)
		addi $sp, $sp, 8
		jr $ra