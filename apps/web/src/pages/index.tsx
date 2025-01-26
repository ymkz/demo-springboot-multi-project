import type { GetServerSidePropsContext, InferGetServerSidePropsType } from 'next'
import { createLoader, parseAsInteger, parseAsIsoDateTime, parseAsString, parseAsStringEnum } from 'nuqs/server'

const booksSearchParams = {
  isbn: parseAsString,
  title: parseAsString,
  status: parseAsString,
  priceFrom: parseAsInteger,
  priceTo: parseAsInteger,
  publishedAtStart: parseAsIsoDateTime,
  publishedAtEnd: parseAsIsoDateTime,
  order: parseAsStringEnum(['+price', '-price', '+published_at', '-published_at']).withDefault('-published_at'),
  offset: parseAsInteger.withDefault(0),
  limit: parseAsInteger.withDefault(20),
}
const loadSearchParams = createLoader(booksSearchParams)

export const getServerSideProps = async (context: GetServerSidePropsContext) => {
  const searchParams = loadSearchParams(context.query)
  console.log(searchParams)

  return {
    props: {
      searchParams,
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
