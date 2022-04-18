export const empty = (array: Array<unknown>) => array.length == 0

export const notEmpty = (array: Array<unknown>) => array.length > 0

export const wrapToArray = <T>(value: T | Array<T>) => (Array.isArray(value) ? value : [value])
