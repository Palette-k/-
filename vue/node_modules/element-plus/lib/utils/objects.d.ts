import type { Entries } from 'type-fest';
export declare const keysOf: <T>(arr: T) => (keyof T)[];
export declare const entriesOf: <T>(arr: T) => Entries<T>;
export { hasOwn } from '@vue/shared';
/** @deprecated TODO: improve it, use lodash */
export declare function getPropByPath(obj: any, path: string, strict: boolean): {
    o: any;
    k: string;
    v: any;
};
