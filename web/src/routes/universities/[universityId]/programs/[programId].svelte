<script lang="ts" context="module">
    import { UniversityProgramApi } from "$lib/university/UniversityProgramApi"
    import { StatusCodes } from "http-status-codes"
    import { container } from "tsyringe"
    import type { Load } from "./[programId]"

    export const load: Load = async ({ params }) => {
        const api = container.resolve(UniversityProgramApi)
        const program = await api.getUniversityProgram(params.universityId, params.programId)

        if (program === null) {
            return { status: StatusCodes.NOT_FOUND }
        } else {
            return {
                status: StatusCodes.OK,
                props: {
                    university: program.university,
                    program: program.program
                }
            }
        }
    }
</script>

<script lang="ts">
    import type { Program } from "$lib/program/Program"
    import type { University } from "$lib/university/University"
    import Title from "$lib/components/Title.svelte"
    import Activities from "$lib/components/universities/Activities.svelte"

    export let university: University
    export let program: Program
</script>

<Title caption={program.name} />

<div class="max-w-2xl m-auto">
    <a class="block underline text-gray-800 p-1" href="/universities/{university.id}">
        ← {university.name}
    </a>
    <h1 class="text-2xl text-gray-800 text-center p-2">
        {program.name}
    </h1>
    <Activities universityId={university.id} programId={program.id} />
</div>
