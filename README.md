# IT Job Market Analyzer (Vietnam & Japan)

A data-driven system for collecting, normalizing, analyzing, and visualizing IT job market data in Vietnam and Japan.

## Objectives
- Collect IT job postings from public sources
- Normalize skills, level, location
- Store data in PostgreSQL
- Provide analytics via REST API
- Visualize insights through dashboard

## Tech Stack
- Python (data processing)
- Spring Boot (Gradle)
- PostgreSQL
- React

## High-level Architecture
Raw Data -> Cleaning -> Normalization -> Database -> Backend API -> Frontend Dashboard

## Project Structure
/docs        -> documentation  
/data        -> datasets  
/scripts     -> data processing scripts  
/backend     -> Spring Boot application  
/frontend    -> React dashboard  

## How to Run
1. Put raw CSV files into data/raw
2. Run scripts in scripts/
3. Start backend
4. Start frontend
