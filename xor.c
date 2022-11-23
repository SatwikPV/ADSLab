#include <stdio.h>
#include <malloc.h>
#include <stdlib.h>
#include <stdint.h>



struct node {
    int data;
    struct node *next;
};

struct node* XOR(struct node *a, struct node *b){
        return (struct node*)((uintptr_t)a ^ (uintptr_t)b)
}

void insert(struct node *head, int ele){
    struct node *curr = head;

    struct node *prev = NULL, *temp = NULL;

    while(curr->next != XOR(prev, NULL){
        temp = curr;
        curr = XOR(curr->next,prev);
        prev = temp;
    }

    struct node *newnode = (struct node*)malloc(sizeof(struct node));

    newnode->data = ele;
    newnode->next = (struct node*)((uintptr_t)(curr) ^ NULL);

    curr->next = (struct node*)((uintptr_t)(curr->next) ^ (uintptr_t)(prev) ^ NULL);
}

void insertpos(struct node *head, int pos, int ele){
     struct node *curr = head;

    struct node *prev = NULL, *temp = NULL;
    int count = 1;
    while(count < pos){
        temp = curr;
        curr = (struct node*)((uintptr_t)(curr->next) ^ (uintptr_t)(prev));
        prev = temp;
        count++;
    }

    struct node *newnode = (struct node*)malloc(sizeof(struct node));
    newnode->data = ele;
    newnode->next = (struct node*)((uintptr_t)(prev) ^ (uintptr_t)(curr));

    prev->next = (struct node*)((uintptr_t)(prev->next) ^ (uintptr_t)(newnode) ^ (uintptr_t)(curr));
    curr->next = (struct node*)((uintptr_t)(curr->next) ^ (uintptr_t)(newnode) ^ (uintptr_t)(prev));
}

int deleteEnd(struct node *head){
     struct node *curr = head;

    struct node *prev = NULL, *temp = NULL;

    while(curr->next != (struct node*)(uintptr_t)(prev) ^ NULL){
        temp = curr;
        curr = (struct node*)((uintptr_t)(curr->next) ^ (uintptr_t)(prev));
        prev = temp;
    }

    prev->next= (struct node*)((uintptr_t)(prev->next) ^ (uintptr_t)(curr) ^ NULL);
    free(curr);
}

int deletepos(struct node* head, 3){
    struct node *curr = head;

    struct node *prev = NULL, *temp = NULL;
    int count = 1;
    while(count < pos){
        temp = curr;
        curr = (struct node*)((uintptr_t)(curr->next) ^ (uintptr_t)(prev));
        prev = temp;
        count++;
    }
    printf("deleting the value %d", &curr->data);
    struct node *nextnode;

    nextnode = (struct node*)((uintptr_t)(curr->next) ^ (uintptr_t)(prev));

    nextnode->next = (struct node*)((uintptr_t)(newnode->next) ^ (uintptr_t)(curr) ^ (uintptr_t)(prev));
    prev->next = (struct node*)((uintptr_t)(prev->next) ^ (uintptr_t)(curr) ^ (uintptr_t)(prev));
}

struct node *create(int ele){
    struct node *newnode = (struct node*)malloc(sizeof(struct node));             //pointer pointing a block of memory of size 'node' is typecasted to 'pointer to a node'...newnode becomes that pointer
    newnode->data = ele;

    return newnode;

};

void display(struct node *head){
    struct node *curr = head;

    struct node *prev = NULL, *temp = NULL;

    while(curr->next != (struct node*)((uintptr_t)(prev) ^ NULL)){
        printf("%d ", curr->data);
        temp = curr;
        curr = (struct node*)((uintptr_t)(curr->next) ^ (uintptr_t)(prev));
        prev = temp;
    }
}

void main(){
    struct node *head;
    int x = 1;
    head = create(x);

    display(head);

    insert(head, 2);
    insert(head, 3);
    insert(head, 9);
    insert(head, 10);
    insert(head, 12);

    display(head);
    deleteEnd(head);

    display(head);

    insertpos(head, 3, 7);

    display(head);
}
