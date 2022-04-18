<script lang="ts" context="module">
    import { UniversityApi } from "$lib/university/UniversityApi"
    import type { Load } from "@sveltejs/kit"
    import { StatusCodes } from "http-status-codes"
    import { container } from "tsyringe"

    export const load: Load = async () => {
        const api = container.resolve(UniversityApi)
        const universities = await api.getUniversities()

        return {
            status: StatusCodes.OK,
            props: { universities }
        }
    }
</script>

<script lang="ts">
    import Title from "$lib/components/Title.svelte"
    import type { University } from "$lib/university/University"
    import { empty } from "$lib/utils/Array"

    export let universities: Array<University>
</script>

<Title caption="Университеты" />

{#if empty(universities)}
    <p class="text-center text-xl text-gray-800 p-2">Университетов нет :(</p>
{:else}
    <div class="max-w-2xl m-auto">
        {#each universities as university}
            <a href="/universities/{university.id}" class="flex justify-between items-center p-2">
                <img class="w-16 h-16 mr-4" src={university.logoUrl} alt="Логотип" />
                <p class="flex-1 underline text-center text-l">{university.name}</p>
            </a>
        {/each}
    </div>
{/if}
