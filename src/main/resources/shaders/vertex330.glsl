#version 330

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 texChoord;

uniform mat4 transform;

out vec2 outTexChoord;

void main() {
    gl_Position = transform * vec4(position, 1.0);
    outTexChoord = texChoord;
}
