#!/bin/bash

#This will be a installation file for the end user with choices.



function start_menu {
    PS3='Please enter your choice: '
    options=("Build all container and start all containers (Install services)" "Build container" "start container" "Quit")
    select opt in "${options[@]}"
    do
        case $opt in
            "Build all container and start all containers (Install services)")
                echo "you chose choice 1"
                ;;
            "Build container")
                echo "you chose choice 2"
                ;;
            "start container")
                echo "you chose choice 3"
                ;;
            "Quit")
                break
                ;;
            *) echo invalid option;;
        esac
    done
}

function build_menu {
    PS3='Please enter your choice: '
    options=("Build all container" "Build specified container"  "Back")
    select opt in "${options[@]}"
    do
        case $opt in
            "Build all container")
                echo "you chose choice 1"
                ;;
            "Build specified container")
                echo "you chose choice 2"
                ;;
            "Back")
                start_menu
                ;;
            *) echo invalid option;;
        esac
    done
}

function  start_container_menu {
    PS3='Please enter your choice: '
    options=("Start all containers" "Start specified container" "Back")
    select opt in "${options[@]}"
    do
        case $opt in
            "start all containers")
                echo "you chose choice 1"
                ;;
            "Start specified container")
                echo "you chose choice 2"
                ;;
            "Back")
                start_menu
                ;;
            *) echo invalid option;;
        esac
    done
}