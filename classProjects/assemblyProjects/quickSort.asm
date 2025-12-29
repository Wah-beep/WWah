## Wah Saw Tamalar
## quickSort.asm
## The quicksort algorithm sorts elements of an array in place. 
## It does this by choosing a value in the array as a 'pivot' value, 
## moving values either to the left or right of the array depending on whether the values are less than or greater than the pivot value,
## respectively, and placing the pivot value in the space in between these two sections of the array. 
## It then recursively calls quicksort on each of the two sections.
## Incompleted

.data
array3: 	.word 	5, 6, 7, 8, 1, 89, 32, 45

.text
main:
	addi $sp, $sp, -4		# Push the stack pointer down
	sw $ra, 0($sp)			# Store $ra into the stack
	
	la $a0, array3			# Base address argument
	addi $a1, $zero, 0		# Starting index, p
	addi $a2, $zero, 7		# Ending index, r
	
	jal quickSort
	
	lw $ra, 0($sp)			# Restore $ra
	addi $sp, $sp, 4		# Pop from the stack
	jr $ra
	
quickSort:
	addi $sp, $sp, -16		# Store into the stack
	sw $ra, 0($sp)			# Save $ra
	sw $s0, 4($sp)			# Save p
	sw $s1, 8($sp)			# Save r
	sw $s2, 12($sp)			# Save q
	
	move $s0, $a1			# Move starting index p to $s0
	move $s1, $a2			# Move ending index r to $s1
	
	slt $t0, $s0, $s1		# Compare p < r 
	beq $t0, $zero, doNothing	# If p > r, doNothing
	
	move $a1, $s0			# p	argument
	move $a2, $s1			# r argument
	jal partitionFun
	move $s2, $v0			# Returns q into $s2
	
	move $a1, $s0			# p argument
	addi $a2, $s2, -1		# q - 1 argument
	jal quickSort
	
	addi $a1, $s2, 1		# q + 1 argument
	move $a2, $s1			# r argument
	jal quickSort

doNothing:
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	lw $s2, 12($sp)
	addi $sp, $sp, 16
	jr $ra
	
partitionFun:
	addi $sp, $sp, -16		# Store into the stack
	sw $ra, 0($sp)			# $ra
	sw $s0, 4($sp)			# Save i
	sw $s1, 8($sp)			# Save j
	sw $s2, 12($sp)			# Save pivot
	
	addi $s0, $a1, -1		# i = p - 1
	move $s1, $a1			# j = p
	sll $t0, $a2, 2			# Shift
	add $t1, $a0, $t0		# Add the offset
	lw $s2, 0($t1)			# Get value array[r], pivot
	move $t4, $a2
	
partitionLoop:
	beq $s1, $t4, loopExit	# j < r, loopExit
	sll $t0, $s1, 2			# Shift
	add $t1, $a0, $t0		# Add the offset
	lw $t2, 0($t1)			# Get value array[j] 
	
	slt $t3, $t2, $s2		# Compare array[j] <= pivot
	beq $t3, $zero, doNone	# If not less, doNone
	
	addi $s0, $s0, 1		# Increment i
	move $a1, $s0			# Arguments
	move $a2, $s1
	jal swapFun				# Jump swapFun
	
doNone:
	addi $s1, $s1, 1		# Increment j
	j partitionLoop
	
loopExit:
	addi $a1, $s0, 1
	move $a2, $t4
	move $a0, $a0
	jal swapFun
	
	addi $v0, $s0, 1
	
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	lw $s2, 12($sp)
	addi $sp, $sp, 16
	jr $ra
	
swapFun:
	addi $sp, $sp, -4
	sw $ra, 0($sp)
	
	sll $t0, $a1, 2
	add $t1, $a0, $t0
	lw $t2, 0($t1)
	
	sll $t3, $a2, 2
	add $t4, $a0, $t3
	lw $t5, 0($t4)
	
	sw $t5, 0($t1)
	sw $t2, 0($t4)
	
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	jr $ra
	

