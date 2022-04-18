<script lang="ts" context="module">
    import { StatusCodes } from "http-status-codes"
    import { container } from "tsyringe"
    import type { Load } from "./[id]"
    import { UniversityApi } from "$lib/university/UniversityApi"

    export const load: Load = async ({ params }) => {
        const api = container.resolve(UniversityApi)
        const university = await api.getUniversity(params.id)

        if (university === null) {
            return { status: StatusCodes.NOT_FOUND }
        } else {
            return {
                status: StatusCodes.OK,
                props: { university }
            }
        }
    }
</script>

<script lang="ts">
    import Title from "$lib/components/Title.svelte"
    import type { University } from "$lib/university/University"
    import Programs from "$lib/components/universities/Programs.svelte"
    import Activities from "$lib/components/universities/Activities.svelte"

    export let university: University
</script>

<Title caption={university.name} />

<h1 class="text-2xl text-gray-800 text-center p-2">{university.name}</h1>

<Programs universityId={university.id} />

<Activities universityId={university.id} />
