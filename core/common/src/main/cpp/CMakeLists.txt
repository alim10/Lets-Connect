cmake_minimum_required(VERSION 3.22.1)
cmake_minimum_required(VERSION 3.10.2)
project("security-native")

add_library(
        native-lib
        SHARED
        native-lib.cpp
)
add_library(
        native-api-urls
        SHARED
        native-api-urls.cpp
)

# Configure compiler flags for security
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++17 -Wall -Wextra -O2")
set(CMAKE_CXX_FLAGS_RELEASE "${CMAKE_CXX_FLAGS_RELEASE} -s -DNDEBUG -fvisibility=hidden")
set(CMAKE_CXX_FLAGS_DEBUG "${CMAKE_CXX_FLAGS_DEBUG} -g -DDEBUG")

# Anti-reverse engineering flags
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -ffunction-sections -fdata-sections")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fstack-protector-strong -D_FORTIFY_SOURCE=2")

find_library(
        log-lib
        log
)

target_link_libraries(
        native-lib
        ${log-lib}
)
target_link_libraries(
        native-api-urls
        ${log-lib}
)