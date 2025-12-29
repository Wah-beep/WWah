## Wah Saw Tamalar

.data
x: .word 10
result: .word 0

.text
main:
	addi $sp, $sp, -4	# push return address onto the stack 
	sw $ra, 0($sp)
	
	lw $a0, x			# load the argument
	jal recFibona		# jump to the recursive fibonacci function
	sw $v0, result		# store the answer into result
	add $a0, $v0, $zero
	li $v0, 1			# print int
	syscall
	
	lw $ra, 0($sp)		# pop the stack
	addi $sp, $sp, 4
	jr $ra
	
recFibona:
	addi $sp, $sp, -12	# push $s0, and $ra onto the stack
	sw $s0, 8($sp)
	sw $s1, 4($sp)
	sw $ra, 0($sp)
	
	add $s0, $a0, $zero	# Move our argument (found in $a0) into safe registers ($s0, $s1)
	add $s1, $a0, $zero
	
	slti $t0, $s0, 1	# $s0 less than 1
	bne $t0, $zero, baseCase	# jump to baseCase
	
	slti $t1, $s1, 3	# s1 less than 3
	bne $t1, $zero, baseCaseTwo	# jump to baseCaseTwo
	
	addi $a0, $s0, -1	# fibonacci (n -1)
	jal recFibona
	move $s1, $v0
	
	addi $a0, $s0, -2	# fibonacci (n -2)
	jal recFibona
	
	add $v0, $v0, $s1
	j restoreAndReturn
	
baseCase:
	add  $v0, $zero, $zero	# return 0
	j restoreAndReturn
	
baseCaseTwo:
	addi $v0, $zero, 1		# return 1
	j restoreAndReturn

restoreAndReturn:
	lw $s0, 8($sp)
	lw $s1, 4($sp)
	lw $ra, 0($sp)
	addi $sp, $sp, 12
	jr $ra

