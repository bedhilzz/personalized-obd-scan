# Description
Android application built to support my engineerings friends' bachelor thesis. It provides a presentation layer to user who owns a car and interest about the fuel economy and brake lifespan related data.

# Key Features
- Basic username and password authentication to differentiate user
- Brake reprot presented in graph
- Fuel economy report presented in graph

# General Flow
1. OBD scanner attached to Raspberry Pi connected to a car
2. Raspberry Pi collects necessary data from the car such as throttle position, speed, engine revolution, etc.
3. Raspberry Pi feeds the collected data to server
4. Android app consumes the data from the server

# Tech Stack
- Android: MVVM, using data binding
- Backend: Django
- OBD scanner: Raspberry Pi with Python script

# Contributor
- Ariz Buditiono: Raspberry Pi guy
- Dwi Nanda Susanto: Backend guy
- Muhammad Fadhillah (myself): Android app guy

# Future Improvement
- Analytics dashboard
