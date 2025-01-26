import type { GetServerSidePropsContext, InferGetServerSidePropsType } from 'next'
import { createLoader, parseAsInteger, parseAsIsoDateTime, parseAsString, parseAsStringLiteral } from 'nuqs/server'
import { FindBooksOrder } from '../generated/openapi/schema'

const booksSearchParams = {
  isbn: parseAsString,
  title: parseAsString,
  status: parseAsString,
  priceFrom: parseAsInteger,
  priceTo: parseAsInteger,
  publishedAtStart: parseAsIsoDateTime,
  publishedAtEnd: parseAsIsoDateTime,
  order: parseAsStringLiteral(Object.keys(FindBooksOrder)).withDefault(FindBooksOrder['-published_at']),
  offset: parseAsInteger.withDefault(0),
  limit: parseAsInteger.withDefault(20),
}
const loadSearchParams = createLoader(booksSearchParams)

export const getServerSideProps = async (context: GetServerSidePropsContext) => {
  const searchParams = loadSearchParams(context.query)

  return {
    props: {
      searchParams: context.query,
    },
  }
}

export default function Page(props: InferGetServerSidePropsType<typeof getServerSideProps>) {
  return (
    <>
      <h1>Page</h1>
      <div>{JSON.stringify(props.searchParams)}</div>
    </>
  )
}
