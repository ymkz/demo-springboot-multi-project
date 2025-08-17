rootProject.name = "demo"

includeBuild(
    "gradle/convention",
)

include(
    ":backend:core",
    ":backend:api",
)
