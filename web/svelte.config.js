import adapter from "@sveltejs/adapter-node"
import preprocess from "svelte-preprocess"

/** @type {import('@sveltejs/kit').Config} */
const config = {
    preprocess: preprocess(),

    kit: {
        adapter: adapter({ out: process.env.outputDir || "build" }),
        target: "#svelte"
    }
}

export default config
