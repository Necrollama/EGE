#version 330

in vec2 outTexChoord;

uniform sampler2D texture_sampler;

void main() {
    vec4 textureColor = texture2D(texture_sampler, outTexChoord.xy);

    if(textureColor.a < 0.5) {
        discard;
    }

    gl_FragColor = textureColor;
}
