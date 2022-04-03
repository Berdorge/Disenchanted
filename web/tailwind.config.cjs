const plugin = require("tailwindcss/plugin")

module.exports = {
    content: ["./src/**/*.{html,svelte}"],
    theme: {
        extend: {}
    },
    variants: {
        extend: {}
    },
    plugins: [
        plugin(({ addVariant }) => {
            addVariant("enabled", "&.enabled")
        })
    ]
}
