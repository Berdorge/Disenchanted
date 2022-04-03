import adapter from "@sveltejs/adapter-node"
import autoprefixer from "autoprefixer"
import path from "path"
import preprocess from "svelte-preprocess"
import tailwind from "tailwindcss"
import swc from "unplugin-swc"
import tailwindConfig from "./tailwind.config.cjs"

/** @type {import('@sveltejs/kit').Config} */
const config = {
    preprocess: preprocess({
        postcss: {
            plugins: [autoprefixer(), tailwind(tailwindConfig)]
        }
    }),

    kit: {
        adapter: adapter({ out: process.env.outputDir || "build" }),
        vite: {
            plugins: [swc.default.vite()],
            resolve: {
                alias: {
                    $lib: path.resolve("./src/lib"),
                    $routes: path.resolve("./src/routes")
                }
            }
        }
    }
}

export default config
