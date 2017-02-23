#!/bin/bash

for f in ../Services/*
do
	docker build ../Services/$f/. -t TheRecruitmentSystem/$f
done